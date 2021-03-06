package de.fu.info.wikisocial.executables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.fu.info.wikisocial.wikidata.extractor.Extractor;
import de.fu.info.wikisocial.wikidata.model.ItemDiscussionPage;
import de.fu.info.wikisocial.NotYetAnalyzedException;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by totucuong-standard on 10/27/16.
 * This programs generate different statistics of Wikidata's items' discussion pages
 * 1. Avg of thread/pages
 * 2. Avg of mentioned items/pages
 * 3. Avg of mentioned properties/pages
 * 4. Avg of users/pages
 */
public class ItemDiscussionPagesStatistics {
//    private int n_msgs;
//    private int n_pages;
//    private int n_item_mentions;
//    private int n_prop_mentions;

    private List<Integer> item_mention_count;
    private List<Integer> prop_mention_count;
    private List<Integer> msg_count;
    DescriptiveStatistics item_stats;
    DescriptiveStatistics prop_stats;
    DescriptiveStatistics msg_stats;
    private boolean analyzed;
    private String path_to_data_file;

    public ItemDiscussionPagesStatistics(String path_to_data_file) {
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
            throw new NotYetAnalyzedException("Need to call ItemDiscussionPagesStatistics.analyze() first");
        return msg_stats.getMean();
    }

    public double std_msg_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call ItemDiscussionPagesStatistics.analyze() first");
        return msg_stats.getStandardDeviation();
    }

    public double avg_item_mention_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call ItemDiscussionPagesStatistics.analyze() first");
        return item_stats.getMean();
    }

    public double std_item_mention_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call ItemDiscussionPagesStatistics.analyze() first");
        return item_stats.getStandardDeviation();
    }

    public double avg_prop_mention_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call ItemDiscussionPagesStatistics.analyze() first");
        return prop_stats.getMean();
    }

    public double std_prop_mention_per_page() throws NotYetAnalyzedException {
        if (!analyzed)
            throw new NotYetAnalyzedException("Need to call ItemDiscussionPagesStatistics.analyze() first");
        return prop_stats.getStandardDeviation();
    }


    public static void main(String[] args) {
        ItemDiscussionPagesStatistics stats = new ItemDiscussionPagesStatistics(args[0]);
        stats.analyze();
        try {
            System.out.format("Average number of messages per page: %f(%f)\n",stats.avg_msg_per_page(), stats.std_msg_per_page());
            System.out.format("Average number of items mentions per page: %f(%f)\n", stats.avg_item_mention_per_page(), stats.std_item_mention_per_page());
            System.out.format("Average number of property mentions per page: %f(%f)\n", stats.avg_prop_mention_per_page(), stats.std_prop_mention_per_page());
        } catch (NotYetAnalyzedException naex) {
            System.out.println(naex.getMessage());
        }
    }

}
