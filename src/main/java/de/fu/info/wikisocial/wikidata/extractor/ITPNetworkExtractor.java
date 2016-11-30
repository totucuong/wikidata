package de.fu.info.wikisocial.wikidata.extractor;

import de.fu.info.wikisocial.wikidata.model.Reply;
import de.fu.info.wikisocial.wikidata.model.Thread;
import de.fu.info.wikisocial.wikidata.model.User;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by totucuong-standard on 11/9/16.
 * The inter-talk-page network (itp-nw) is a weighted digraph $G_{ip}=(V_{ip},E_{ip})$, where $V_{ip}$ is a set of
 * nodes representing users and $E_{inter}$ is a set of directed edges. A directed edge $a \rightarrow b$ represents
 * the fact that user $a$ posted at least a message on user $b$'s talk page. The weight of an edge is the number of
 * messages were posted by $a$ on $b$'s talk page.
 */
public class ITPNetworkExtractor {
    private String filename;

//    private ArrayList<Thread> threads;

    private ArrayList<Pair<String, String>> edges;

    private HashMap<String, ArrayList<Thread>> userToThread;


    /**
     * Construct a ITPNetwork extractor with a path to graph file.
     *  @param filename name of the file that store the WTPNetwork
     */
    public ITPNetworkExtractor(String filename) {
//        threads = new ArrayList<>();
        edges = new ArrayList<>();
        this.filename = filename;
        userToThread = new HashMap<>();
    }

    /**
     * extract the Within-Page network from talk pages
     * @param users list of users
     */
    public void extract(List<User> users) throws IOException {
        // get discussion threads
        for (User u : users) {
            System.out.println("DEBUG - Process talk page of user " + u.getUser_name());
            ArrayList<Thread> tmp = (new TalkPageExtractor(u.getTalk_page(), u.getUser_name())).get_threads();
            if (tmp != null) {
                userToThread.put(u.getUser_name(), tmp);
            }

        }

        // extract ITPNetwork's edges
        for (String user : userToThread.keySet()) {
            for (Thread t : userToThread.get(user)) {
                extract_edges(user, t);
            }
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
    private void extract_edges(String user, Thread t) throws IOException {
        extract_edges(user, t.getReply());
    }

    /**
     * Extract edges from a Reply
     * @param reply a reply
     */
    private void extract_edges(String user, Reply reply) throws IOException {
        String poster = reply.get_poster();
        if (reply.get_poster() != null && !poster.equals(user)) {
            edges.add(Pair.of(poster, user));
//            System.out.println("DEBUG -- adding one edge " + poster + " " + user);
        }

        ArrayList<Reply> sub_replies = reply.get_replies();
        if (sub_replies != null) {
            for (Reply r : sub_replies) {
                if (r.get_poster() != null && !r.get_poster().equals(user)) {
                    edges.add(Pair.of(r.get_poster(), user));
//                    System.out.println("DEBUG -- adding one edge " + r.get_poster() + " " + user);
                }
                extract_edges(user, r);
            }
        }
    }
}
