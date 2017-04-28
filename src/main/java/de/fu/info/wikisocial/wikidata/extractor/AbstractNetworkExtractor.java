package de.fu.info.wikisocial.wikidata.extractor;

import de.fu.info.wikisocial.wikidata.model.Reply;
import de.fu.info.wikisocial.wikidata.model.TemporalEdge;
import de.fu.info.wikisocial.wikidata.model.Thread;
import de.fu.info.wikisocial.wikidata.model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by totucuong-standard on 1/20/17.
 */
public abstract class  AbstractNetworkExtractor {
    protected String filename;
    protected ArrayList<Thread> threads;
    protected ArrayList<TemporalEdge> edges;
    private final String prop_dis_page_prefix = "https://www.wikidata.org/wiki/Property_talk:";

    public AbstractNetworkExtractor(String filename) {
        this.filename = filename;
        threads = new ArrayList<>();
        edges = new ArrayList<>();
    }

    /**
     * extract the ContentNetwork from talk pages
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

        for (Thread t : threads) {
            extract_edges(t);
        }
    }

    public void extract(ArrayList<String> properties) throws IOException {
        for (String p : properties) {
            TalkPageExtractor extractor = new TalkPageExtractor(new URL(this.prop_dis_page_prefix+p),p);
            ArrayList<Thread> threads = extractor.get_threads();
            if (threads != null)
                for (Thread t : extractor.get_threads()) {
                    this.extract_edges(t);
                }
        }
    }

    /**
     * Extract graph edges for the a within page network
     * @param t a discussion thread
     */
    void extract_edges(Thread t) throws IOException {
        extract_edges(t.getReply(), t.getOwner());
    }


    /**
     * Extract edges from a Reply
     * @param reply a reply
     */
    abstract void extract_edges(Reply reply, String owner) throws IOException;

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
                for (String a : e.getItems()) {
                    writer.write(";" + a);
                }
                for (String a : e.getProps())
                    writer.write(";" + a);
                writer.write(";" + e.getContext());
                writer.write("\n");
            }
        } catch (IOException iex) {
            iex.printStackTrace();
        }
    }
}
