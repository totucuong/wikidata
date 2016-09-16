package de.fu.info.wikisocial.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by totucuong-standard on 9/16/16.
 * This graph implement a symbol graph API, i.e., the graph's vertices have names.
 */
public class SymbolGraph {

    // name to index
    private HashMap<String, Integer> st;

    // index to name
    private ArrayList<String> keys;

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
        keys = new ArrayList<>(st.size());
        st.forEach((k,v) -> keys.add(v,k));

        // builds the graph
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            if (directed)
                G = new DigraphBuilderImpl().createGraph(st.size());
            else
                G = new GraphBuilderImpl().createGraph(st.size());
            String l;
            while ((l = reader.readLine()) != null) {
                String[] vertices =l.split(delim);
                G.addEdge(Integer.parseInt(vertices[0]), Integer.parseInt(vertices[1]));
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
    int index(String key) {
        return st.get(key);
    }

    /**
     *
     * @param v the index of a vertex
     * @return the vertex's key (name)
     */
    String name(int v) {
        return keys.get(v);
    }

    /**
     *
     * @return the underlying graph
     */
    Graph G() {
        return G;
    }
}
