package de.fu.info.wikisocial.wikidata.networks;

import de.fu.info.wikisocial.graph.SymbolGraph;

import java.io.IOException;

/**
 * Created by totucuong-standard on 9/19/16.
 * This class implements the Inter Talk Page Network API. An inter-talk-page network is a weighted digraph whose nodes
 * representing users and directed edges (A->B) representing the fact that user A posted message to user B on user B's
 * talk page.
 */
public class ITPNetwork extends SymbolGraph {

    public ITPNetwork(String filename, String delim) throws IOException {
        super(filename, delim, true); // create directed graph
    }
}
