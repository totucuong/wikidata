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
public class WtpNetworkExtractor extends AbstractNetworkExtractor {

    /**
     * Construct a WTPNetwork extractor with a path to graph file.
     *  @param filename name of the file that store the WTPNetwork
     */
    public WtpNetworkExtractor(String filename) {
        super(filename);
    }

//    void extract_edges(Thread t) throws IOException {
//        extract_edges(t.getReply(), t.getOwner());
//    }

    public void extract_edges(Reply reply, String owner) throws IOException {
        String poster = reply.get_poster();
        if (reply.get_timestamp() != null) {
            TemporalEdge e = new TemporalEdge(poster, owner, LocalDate.parse(reply.get_timestamp(),
                    DateTimeFormatter.ofPattern("d[d] MMMM yyyy")));
            e.setItems(reply.getItems());
            e.setProps(reply.getProps());
            e.setContext(reply.getQuestion());
            edges.add(e);
        }

        String newOwner = poster;
        ArrayList<Reply> sub_replies = reply.get_replies();
        if (sub_replies != null) {
            for (Reply r : sub_replies) {
                extract_edges(r,newOwner);
            }
        }
    }
}
