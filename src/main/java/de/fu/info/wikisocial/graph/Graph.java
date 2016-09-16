package de.fu.info.wikisocial.graph;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by totucuong-standard on 9/16/16.
 * This class implement Graph API using adjacency list as the underlying data structure.
 */
public class Graph {

    // number of vertices
    private final int V;

    // number of edges
    private int E;

    // adjacency list data structure
    private ArrayList<ArrayList<Integer>> adj;

    /**
     *
     * @param v number of vertices.
     */
    public Graph(int v) {
        this.V = v;
        this.E = 0;
        initAdj();
    }

    /**
     *
     * @param reader a BufferReader to read graph from a text file.
     */
    public Graph(BufferedReader reader) throws IOException {
        this.V = Integer.parseInt(reader.readLine());
        int E = Integer.parseInt(reader.readLine());
        initAdj();
        for (int i = 0; i < E; i++) {
            // add an edge
            String[] edge = reader.readLine().split(" ");
            addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
        }
    }

    private void initAdj() {
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++)
            adj.add(i, new ArrayList<>());
    }

    /**
     *
     * @return the number of vertices.
     */
    int V() {
        return V;
    }

    /**
     *
     * @return the number of edges
     */
    int E() {
        return E;
    }

    /**
     *
     * @param v the index of a vertex
     * @return return v's list of adjancent vertices
     */
    Iterable<Integer> adj(int v) {
        return adj.get(v);
    }

    /**
     * Add an edge to the graph
     * @param v source vertex
     * @param w target vertex
     */
    void addEdge(int v, int w) {
        adj.get(v).add(new Integer(w));
        adj.get(w).add(new Integer(v));
        E++;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(V).append(" vertices, ").append(" E ").append(" edges\n");
        for (int v = 0; v < V; v++) {
            builder.append(v).append(": ");
            adj.get(v).forEach(w -> builder.append(w).append(" "));
            builder.append("\n");
        }
        return builder.toString();
    }
}