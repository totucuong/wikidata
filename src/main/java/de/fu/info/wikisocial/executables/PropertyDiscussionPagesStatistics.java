package de.fu.info.wikisocial.executables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.fu.info.wikisocial.NotYetAnalyzedException;
import de.fu.info.wikisocial.wikidata.extractor.Extractor;
import de.fu.info.wikisocial.wikidata.extractor.TalkPageExtractor;
import de.fu.info.wikisocial.wikidata.model.ItemDiscussionPage;
import de.fu.info.wikisocial.wikidata.model.Thread;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by totucuong-standard on 10/28/16.
 * This programs generate different statistics of Wikidata's properties' discussion pages
 * 1. Avg of thread/pages
 * 2. Avg of mentioned items/pages
 * 3. Avg of mentioned properties/pages
 * 4. Avg of users/pages
 */
public class PropertyDiscussionPagesStatistics {
    private List<Integer> item_mention_count;
    private List<Integer> prop_mention_count;
    private List<Integer> msg_count;
    DescriptiveStatistics item_stats;
    DescriptiveStatistics prop_stats;
    DescriptiveStatistics msg_stats;
    private boolean analyzed;
    private String path_to_data_file;
    private ArrayList<Pair<String, String>> edges;


    public PropertyDiscussionPagesStatistics(String path_to_data_file) {
//        this.n_msgs = 0;
//        this.n_pages = 0;
//        this.n_prop_mentions = 0;
//        this.n_item_mentions = 0;
        item_mention_count = new ArrayList<>();
        prop_mention_count = new ArrayList<>();
        msg_count = new ArrayList<>();
        item_stats = new DescriptiveStatistics();
        prop_stats = new DescriptiveStatistics();
        msg_stats = new DescriptiveStatistics();
        this.path_to_data_file = path_to_data_file;
        this.analyzed = false;
        this.edges = new ArrayList<>();
    }


    public void save(String graphfilename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(graphfilename))) {
//            writer.write("Source,Target\n");
            for (Pair p : edges) {
                String left;
                if (p.getLeft() != null)
                    left = ((String) p.getLeft()).replace(" ", "_").trim();
                else
                    continue;
//                    left = "null";

                String right;
                if (p.getRight() != null)
                    right = ((String) p.getRight()).replace(" ", "_").trim();
                else
                    continue;
//                    right = "null";
                writer.write(left + "," + right);
                writer.write("\n");
            }
        } catch (IOException iex) {
            iex.printStackTrace();
        }
    }

    /**
     * Extract graph edges for the a within page network
     * @param t a discussion thread
     */
    private void extract_edges(Thread t) throws IOException {
        // extract all artifacts
        Set<String> artifacts = Extractor.extract_artifacts(t.toString());

        // create edges between every two artifacts
        ArrayList<String> nodes = new ArrayList<>();
        nodes.addAll(artifacts);
        System.out.println("Number of nodes: " + nodes.size());
        for (int i = 0; i < nodes.size() - 1; i++)
            for (int j = i+1; j < nodes.size(); j++) {
                edges.add(Pair.of(nodes.get(i), nodes.get(j)));
            }
    }

    public void analyze() {
        analyzed = true;
//        double item_mentions = 0;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        int process = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path_to_data_file));
            String line;
            while ((line = reader.readLine()) != null) {
                ItemDiscussionPage page = gson.fromJson(line, ItemDiscussionPage.class);
                String content = Jsoup.parse(page.getHtml()).text();
//                n_msgs += Extractor.count_num_msgs(content);
//                n_item_mentions += Extractor.count_num_item_mentions(content);
//                n_prop_mentions += Extractor.count_num_prop_mentions(content);
//                n_pages++;
                msg_count.add(Extractor.count_num_msgs(content));
                item_mention_count.add(Extractor.count_num_item_mentions(content));
                prop_mention_count.add(Extractor.count_num_prop_mentions(content));
                process++;
                if (process % 100 == 0)
                    System.out.println("Processing page number " + process);
            }
            // statistics for item mentions
            item_mention_count.forEach(c -> item_stats.addValue(c));

            // statistics for property mentions
            prop_mention_count.forEach(c -> prop_stats.addValue(c));

            // statistics for message count
            msg_count.forEach(c -> msg_stats.addValue(c));

        } catch (FileNotFoundException fex) {
            System.err.println("Cannot find data file " + path_to_data_file);
            return;
        } catch (IOException iex) {
            System.err.println("Something wrong when reading file " + path_to_data_file);
            return;
        }
    }

    public double avg_msg_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call PropertyDiscussionPagesStatistics.analyze() first");
        return msg_stats.getMean();
    }

    public double std_msg_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call PropertyDiscussionPagesStatistics.analyze() first");
        return msg_stats.getStandardDeviation();
    }

    public double avg_item_mention_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call PropertyDiscussionPagesStatistics.analyze() first");
        return item_stats.getMean();
    }

    public double std_item_mention_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call PropertyDiscussionPagesStatistics.analyze() first");
        return item_stats.getStandardDeviation();
    }

    public double avg_prop_mention_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call PropertyDiscussionPagesStatistics.analyze() first");
        return prop_stats.getMean();
    }

    public double std_prop_mention_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call PropertyDiscussionPagesStatistics.analyze() first");
        return prop_stats.getStandardDeviation();
    }


    public static void main(String[] args) {
        PropertyDiscussionPagesStatistics stats = new PropertyDiscussionPagesStatistics(args[0]);
        stats.analyze();
        try {
            System.out.println("Stats for Property namespace");
            System.out.format("Average number of messages per page: %f(%f)\n",stats.avg_msg_per_page(), stats.std_msg_per_page());
            System.out.format("Average number of items mentions per page: %f(%f)\n", stats.avg_item_mention_per_page(), stats.std_item_mention_per_page());
            System.out.format("Average number of property mentions per page: %f(%f)\n", stats.avg_prop_mention_per_page(), stats.std_prop_mention_per_page());
        } catch (NotYetAnalyzedException naex) {
            System.out.println(naex.getMessage());
        }
    }
}
