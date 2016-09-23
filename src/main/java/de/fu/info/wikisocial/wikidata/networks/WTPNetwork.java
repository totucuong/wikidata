package de.fu.info.wikisocial.wikidata.networks;

import de.fu.info.wikisocial.graph.SymbolGraph;

import java.io.IOException;

/**
 * Created by totucuong-standard on 9/19/16.
 * This class implements the Within Talk Page network API. A within-talk-page network has nodes representing users. Each
 * of its edge (A->B) represents the fact that A post a message to B on some user talk pages. Each edge is weighted
 * by the number of messages with the aforementioned semantics.
 */
public class WTPNetwork extends SymbolGraph {

    /**
     * Construct a symbol graph from a file
     *
     * @param filename to read graph from a text file.
     * @param delim    is the delimiter character that separate vertex names
     */
    public WTPNetwork(String filename, String delim) throws IOException {
        super(filename, delim, true); // create directed symbol graph
    }
}
