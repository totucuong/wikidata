package de.fu.info.wikisocial.wikidata.extractor;

import de.fu.info.wikisocial.wikidata.model.Reply;
import de.fu.info.wikisocial.wikidata.model.TemporalEdge;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by totucuong-standard on 1/20/17.
 * Extract within discussion page communication network in property namespace
 */
public class WppNetworkExtractor extends AbstractNetworkExtractor {

    public WppNetworkExtractor(String filename) {
        super(filename);
    }

    void extract_edges(Reply reply, String owner) throws IOException {
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
}
