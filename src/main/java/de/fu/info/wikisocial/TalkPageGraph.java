package de.fu.info.wikisocial;

import jdk.internal.dynalink.support.Lookup;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Created by totucuong on 8/2/16.
 */
public class TalkPageGraph {
    private TalkPage page;
    private GraphModel graph_model;

    // social graph for the current talk page
    private DirectedGraph social_graph;


    public TalkPageGraph(TalkPage page) {
        this.page = page;
        graph_model = org.openide.util.Lookup.getDefault().lookup(GraphController.class).getGraphModel();
    }

    // @TODO: remove owner for the list of user
    // @TODO: continue here
    private void build_graph() {
        ArrayList<String> threads = page.get_threads();
        ArrayList<Node> nodes = new ArrayList<>();
        for (String t : threads) {
            DiscussionThread thread = new DiscussionThread(t);
            HashSet<String> users = thread.extract_users();
            for (String u : users) {
                Node node = graph_model.factory().newNode();
                node.setLabel(u);
            }


//            HashSet<String> properties = thread.extract_properties();
//            HashSet<String> items = thread.extract_items();
        }
    }
}
