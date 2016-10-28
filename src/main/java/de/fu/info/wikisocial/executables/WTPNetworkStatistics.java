package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.graph.Graph;
import de.fu.info.wikisocial.graph.GraphStatistics;
import de.fu.info.wikisocial.graph.SymbolGraph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by totucuong-standard on 10/21/16.
 * This program collect statistics about Wikidata's within-talk-page network
 */
public class WTPNetworkStatistics {

    public static void main(String[] args) throws Exception {
        System.out.println("Construct within page network from " + args[0]);
        String filename = args[0];
        String delim = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delim, true);
        Graph g = sg.G();

        System.out.println("Number of nodes: " + g.V());
        System.out.println("Number of edges: " + g.E());
        System.out.println("Average degree: " + GraphStatistics.avgDegree(g));


    }
}
