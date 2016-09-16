package de.fu.info.wikisocial.graph;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by totucuong-standard on 9/16/16.
 * This class implement a directed graph API.
 */
public class Digraph extends Graph {

    /**
     * Construct a graph with a predifined number of vertices
     * @param V the number of vertices of the graph
     */
    public Digraph(int V) {
        super(V);
    }

    /**
     * Construct a graph from file
     * @param reader that read the graph from a file
     * @throws IOException
     */
    public Digraph(BufferedReader reader) throws IOException {
        super(reader);
    }

    /**
     * Add edge (v->w) to the graph
     * @param v source vertex
     * @param w target vertex
     */
    @Override
    void addEdge(int v, int w) {
        adj.get(v).add(w);
        E++;
    }

    /**
     * Compute the revesed digraph of the current graph
     * @return a digraph
     */
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : adj(v))
                R.addEdge(w, v);
        return R;
    }
}
