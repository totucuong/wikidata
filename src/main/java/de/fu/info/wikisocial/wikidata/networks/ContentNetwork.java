package de.fu.info.wikisocial.wikidata.networks;

import de.fu.info.wikisocial.graph.SymbolGraph;

import java.io.IOException;

/**
 * Created by totucuong-standard on 9/19/16.
 * This class implements the Content Network API. A content network is a weighted graph whose nodes presenting Wikidata
 * items/properties and edges representing the fact that two items/properties were mentioned in the same message on
 * talk pages. Each edge is weighted by the number of co-locations of the two items/properties.
 */
public class ContentNetwork extends SymbolGraph {

    public ContentNetwork(String filename, String delim, boolean directed) throws IOException {
        super(filename, delim, false); // create undirected symbol graph
    }
}
