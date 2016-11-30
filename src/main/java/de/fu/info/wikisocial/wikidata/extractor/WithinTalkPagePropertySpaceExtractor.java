package de.fu.info.wikisocial.wikidata.extractor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.fu.info.wikisocial.wikidata.model.ItemDiscussionPage;
import de.fu.info.wikisocial.wikidata.model.Reply;
import de.fu.info.wikisocial.wikidata.model.Thread;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by totucuong-standard on 11/30/16.
 * Extract content network (see paper for definition) from properties' discussion page, i.e., property namespace.
 */
public class WithinTalkPagePropertySpaceExtractor {

    private String input_file;

    private String output_file;

    private ArrayList<Thread> threads;

    private ArrayList<Pair<String, String>> edges;

    /**
     *
     * @param input_file stores discussion page of properties
     * @param output_file stores content network: a list of its edges.
     */
    public WithinTalkPagePropertySpaceExtractor(String input_file, String output_file) {
        threads = new ArrayList<>();
        edges = new ArrayList<>();
        this.input_file = input_file;
        this.output_file = output_file;
    }

    /**
     * generate content network
     */
    public void generate() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.input_file));
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (count % 100 == 0) {
                    System.out.println("Processing property " + count + "th");
//                    System.out.println("Number of edges: " + edges.size());
                }
                count++;
                ItemDiscussionPage page = gson.fromJson(line, ItemDiscussionPage.class);
                TalkPageExtractor extractor = new TalkPageExtractor(page.getHtml(), page.getItem_id());
                ArrayList<Thread> threads = extractor.get_threads();
                if (threads != null)
                    for (Thread t : extractor.get_threads()) {
                        this.extract_edges(t);
                    }
            }

        } catch (FileNotFoundException fex) {
            System.err.println("Cannot find data file " + input_file);
            return;
        } catch (IOException iex) {
            System.err.println("Something wrong when reading file " + input_file);
            return;
        }

    }

    /**
     * save content network
     */
    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output_file))) {
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

    public ArrayList<Pair<String, String>> getEdges() {
        return edges;
    }

    /**
     * Extract graph edges for the a within page network
     * @param t a discussion thread
     */
    private void extract_edges(Thread t) throws IOException {
        extract_edges(t.getReply());
    }

    /**
     * Extract edges from a Reply
     * @param reply a reply
     */
    private void extract_edges(Reply reply) throws IOException {
        String poster = reply.get_poster();

        ArrayList<Reply> sub_replies = reply.get_replies();
        if (sub_replies != null) {
            for (Reply r : sub_replies) {
                edges.add(Pair.of(r.get_poster(), poster));
                extract_edges(r);
            }
        }
    }
}
