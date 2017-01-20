package de.fu.info.wikisocial.wikidata.extractor;

import de.fu.info.wikisocial.wikidata.model.Reply;
import de.fu.info.wikisocial.wikidata.model.TemporalEdge;
import de.fu.info.wikisocial.wikidata.model.User;
import de.fu.info.wikisocial.wikidata.model.Thread;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;

/**
 * Created by totucuong-standard on 9/22/16.
 */
public class WTPNetworkExtractor {

    private String filename;

    private ArrayList<Thread> threads;

    private ArrayList<TemporalEdge> edges;
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
     * extract the ContentNetwork from talk pages
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

    /**
     * Extract graph edges for the a within page network
     * @param t a discussion thread
     */
    private void extract_edges(Thread t) throws IOException {
        extract_edges(t.getReply(), t.getOwner());
    }

    /**
     * Extract edges from a Reply
     * @param reply a reply
     */
    private void extract_edges(Reply reply, String owner) throws IOException {
        String poster = reply.get_poster();
        if (reply.get_timestamp() != null)
            edges.add(new TemporalEdge(poster, owner, LocalDate.parse(reply.get_timestamp(),
                    DateTimeFormatter.ofPattern("d[d] MMMM yyyy"))));

        ArrayList<Reply> sub_replies = reply.get_replies();
        if (sub_replies != null) {
            for (Reply r : sub_replies) {
                if (r.get_timestamp() != null)
                    edges.add(new TemporalEdge(r.get_poster(), poster, LocalDate.parse(r.get_timestamp(),
                            DateTimeFormatter.ofPattern("d[d] MMMM yyyy"))));
                extract_edges(r,owner);
            }
        }
    }

    public void save() {
        // sort edges
        Collections.sort(edges);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
//            writer.write("Source,Target\n");
            for (TemporalEdge e : edges) {
                String src;
                if (e.getSrc() != null)
                    src = e.getSrc().replace(" ", "_").trim();
                else
                    continue;
//                    left = "null";

                String tgt;
                if (e.getTgt() != null)
                    tgt = e.getTgt().replace(" ", "_").trim();
                else
                    continue;
//                    right = "null";
                writer.write(src + ";" + tgt);
                writer.write(";"+ e.getTimestamp().format(DateTimeFormatter.ISO_DATE));
                writer.write("\n");
            }
        } catch (IOException iex) {
            iex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ArrayList<String> strings = null;
        for (String str : strings)
            System.out.println(str);
    }
}
