package de.fu.info.wikisocial.graph;

/**
 * Created by totucuong-standard on 9/16/16.
 */
public class GraphBuilderImpl implements GraphBuilder {
    @Override
    public Graph createGraph(int V) {
        return (new Graph(V));
    }
}
