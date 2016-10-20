package de.fu.info.wikisocial.wikidata.extractor;

import de.fu.info.wikisocial.wikidata.model.Reply;
import de.fu.info.wikisocial.wikidata.model.User;
import de.fu.info.wikisocial.wikidata.model.Thread;
import org.apache.batik.css.engine.value.css2.SrcManager;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.helpers.FormattingTuple;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by totucuong-standard on 9/22/16.
 */
public class WTPNetworkExtractor {

    private String filename;

    private ArrayList<Thread> threads;

    private ArrayList<Pair<String, String>> edges;


    /**
     * Construct a WTPNetwork extractor with a path to graph file.
     *  @param filename name of the file that store the WTPNetwork
     */
    public WTPNetworkExtractor(String filename) {
        threads = new ArrayList<>();
        edges = new ArrayList<>();
        this.filename = filename;
    }

    /**
     * extract the Within-Page network from talk pages
     * @param users list of users
     */
    public void extract(List<User> users) throws IOException{
        // get discussion threads
        for (User u : users) {
            System.out.println("DEBUG - Process talk page of user " + u.getUser_name());
            ArrayList<Thread> tmp = (new TalkPageExtractor(u.getTalk_page(), u.getUser_name())).get_threads();
            if (tmp != null)
                threads.addAll(tmp);
        }

        // extract WTPNetwork's edges
        for (Thread t : threads) {
//            System.out.println(t.getTitle());
            extract_edges(t);
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

    public static void main(String[] args) {
        ArrayList<String> strings = null;
        for (String str : strings)
            System.out.println(str);
    }
}
