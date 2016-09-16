package de.fu.info.wikisocial.graph;

/**
 * Created by totucuong-standard on 9/16/16.
 */
public class DigraphBuilderImpl implements GraphBuilder {
    @Override
    public Graph createGraph(int V) {
        return (new Digraph(V));
    }
}
