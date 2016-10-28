package de.fu.info.wikisocial.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by totucuong-standard on 9/16/16.
 * This graph implement a symbol graph API, i.e., the graph's vertices have names.
 */
public class SymbolGraph {

    // name to index
    private HashMap<String, Integer> st;

    // index to name
    private String[] keys;

    // underlying graph
    private Graph G;

    /**
     * Construct a symbol graph from a file
     * @param filename  to read graph from a text file.
     * @param delim is the delimiter character that separate vertex names
     * @param directed true if the graph is directed, false otherwise
     */
    public SymbolGraph(String filename, String delim, boolean directed) throws IOException {
        // building symbol table
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            st = new HashMap<>();
            String l;
            while ((l = reader.readLine()) != null) {
                String[] vertices = l.split(delim);
                for (int i = 0; i < vertices.length; i++)
                    if (!st.containsKey(vertices[i]))
                        st.put(vertices[i], st.size());
            }
        }

        // building inverted index
        keys = new String[st.size()];
//        for (String k : st.keySet()) {
//            keys.add(st.get(k), k);
//        }
        st.forEach((k,v) -> keys[v] = k);

        // builds the graph
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            if (directed)
                G = new DigraphBuilderImpl().createGraph(st.size());
            else
                G = new GraphBuilderImpl().createGraph(st.size());
            String l;
            while ((l = reader.readLine()) != null) {
                String[] vertices = l.split(delim);         // connecting the
                int v = st.get(vertices[0]);                // first vertex
                for (int i = 1; i < vertices.length; i++)   // on each line
                    G.addEdge(v, st.get(vertices[i]));      // to all others.
            }
        }
    }

    /**
     *
     * @param key is the name of a vertex
     * @return true if the graph contain the vertex, false otherwise
     */
    boolean contains(String key) {
        return st.containsKey(key);
    }

    /**
     *
     * @param key is the name of a vertex
     * @return the associated index of the vertex in the graph
     */
    public final int index(String key) {
        return st.get(key);
    }

    /**
     *
     * @param v the index of a vertex
     * @return the vertex's key (name)
     */
    public final String name(int v) {
        return keys[v];
    }

    /**
     *
     * @return the underlying graph
     */
    public final Graph G() {
        return G;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Construct within page network from " + args[0]);
        String filename = args[0];
        String delim = args[1];
            SymbolGraph sg = new SymbolGraph(filename, delim, true);
            Graph g = sg.G();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter a node name:");
            String source = reader.readLine();
            if (source == "q")
                return;

            for (int w : g.adj(sg.index(source))) {
                System.out.println("    " + sg.name(w));
            }
        }
    }
}
