package de.fu.info.wikisocial.wikidata.extractor;

import de.fu.info.wikisocial.wikidata.model.Thread;
import de.fu.info.wikisocial.wikidata.model.User;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by totucuong-standard on 11/23/16.
 * This is a co-mentioning network. Nodes are Wikidata items/properties. A edge between two Wikidata
 * artifacts if they are co-mentioned in the same discussion thread from a talk pages
 */
public class ContentNetworkExtractor {

    private String filename;

    private ArrayList<Thread> threads;

    private ArrayList<Pair<String, String>> edges;

    /**
     * Construct a ContentNetworkExtractor  with a path to graph file.
     *  @param filename name of the file that store the WTPNetwork
     */
    public ContentNetworkExtractor(String filename) {
        this.filename = filename;
        this.threads = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * extract the ContentNetworkExtractor from talk pages
     * @param users list of users
     */
    public void extract(List<User> users) throws IOException {
        // get discussion threads
        for (User u : users) {
            System.out.println("DEBUG - Process talk page of user " + u.getUser_name());
            ArrayList<Thread> tmp = (new TalkPageExtractor(u.getTalk_page(), u.getUser_name())).get_threads();
            if (tmp != null)
                threads.addAll(tmp);
        }

        // extract ContentNetworkExtractor's edges
        for (Thread t : threads) {
//            System.out.println(t.getTitle());
            extract_edges(t);
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
        for (int i = 0; i < nodes.size() - 1; i++)
            for (int j = i+1; j < nodes.size(); j++) {
                edges.add(Pair.of(nodes.get(i), nodes.get(j)));
            }
    }


    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
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
}
