package de.fu.info.wikisocial.graph;

/**
 * Created by totucuong-standard on 9/16/16.
 * This class provide some functions to collects statistics about a graph
 */
public class GraphStatistics {

    /**
     * Compute degree of a vertex in an undirected graph
     * @param G a graph
     * @param v a vertex
     * @return degree of vertex v
     */
    public static int degree(Graph G, int v) {
        int degree = 0;

        for (int w : G.adj(v)) degree++;
        return degree;
    }

    /**
     * Compute maximum vertex degree in a given graph
     * @param G a graph
     * @return maximum degree of a vertex in G
     */
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > max)
                max = degree(G, v);
        }
        return max;
    }

    /**
     * Compute average vertex degree in a given graph. Here the graph is always considered undirected
     * @param G a graph
     * @return
     */
    public static int avgDegree(Graph G) {
        return (2 * G.E() / G.V());
    }
}
