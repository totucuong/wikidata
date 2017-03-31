package de.fu.info.wikisocial.wikidata.extractor;

import de.fu.info.wikisocial.wikidata.model.Reply;
import de.fu.info.wikisocial.wikidata.model.TemporalEdge;
import de.fu.info.wikisocial.wikidata.model.Thread;
import de.fu.info.wikisocial.wikidata.model.User;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by totucuong-standard on 1/20/17.
 */
public class ContentNetworkExtractor extends AbstractNetworkExtractor {
    public ContentNetworkExtractor(String filename) {
        super(filename);
    }

    void extract_edges(Reply reply, String owner) throws IOException {
        if (reply.get_timestamp() != null) {
            Set<String> artifacts = Extractor.extract_artifacts(reply.getQuestion());
            // create edges between every two artifacts
            ArrayList<String> nodes = new ArrayList<>();
            nodes.addAll(artifacts);
            for (int i = 0; i < nodes.size() - 1; i++)
                for (int j = i+1; j < nodes.size(); j++) {
                    edges.add(new TemporalEdge(nodes.get(i), nodes.get(j), LocalDate.parse(reply.get_timestamp(),
                            DateTimeFormatter.ofPattern("d[d] MMMM yyyy"))));
                }
        }

        ArrayList<Reply> sub_replies = reply.get_replies();
        if (sub_replies != null) {
            for (Reply r : sub_replies) {
                extract_edges(r, owner);
            }
        }
    }
}
