//package de.fu.info.wikisocial.wikidata;
//
//import de.fu.info.wikisocial.wikidata.extractor.TalkPageExtractor;
//import org.gephi.graph.api.*;
//import org.gephi.project.api.ProjectController;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//
///**
// * Created by totucuong on 8/2/16.
// * Each talk page has a collection of threads.
// * Each threads has a social graph.
// * Hence each talk page has a collection of social graphs.
// * We do not filter out thread that has no replies, i.e., an user i post a msg to user j but there were no replies.
// */
//public class TalkPageGraph {
//    // user talk page
//    private TalkPageExtractor page;
//
//    // Gelphi project controller
//    ProjectController pc;
//    // Gelphi graph model factory
//    private GraphModel graph_model;
//
//    // social graph for the current talk page
//    private ArrayList<DirectedGraph> graphs;
//
//    public TalkPageGraph(TalkPageExtractor page) {
//        this.page = page;
//        pc = org.openide.util.Lookup.getDefault().lookup(ProjectController.class);
//        pc.newProject();
//        graph_model = org.openide.util.Lookup.getDefault().lookup(GraphController.class).getGraphModel();
//        graphs = new ArrayList<>();
//    }
//
//    private void build_graph() {
////        ArrayList<String> threads = page.get_threads();
//        for (String t : threads) {
//            DiscussionThread thread = new DiscussionThread(t, page.get_owner());
//
//            // create nodes
//            ArrayList<Node> nodes = new ArrayList<>();
//            HashMap<String, Node> uname_to_node = new HashMap<>();
//            HashSet<String> users = thread.extract_users();
//
//            // in case no replies from owner.
//            users.add(page.get_owner());
//
////            if (users.size() < 2) {
////                System.out.println(t);
////                System.out.println("=======================");
////                continue;
////            }
//
//
//            for (String u : users) {
//                Node node = graph_model.factory().newNode();
//                node.setLabel(u);
//                uname_to_node.put(u, node);
//                nodes.add(node);
//            }
//
//            // create edges
//            ArrayList<Edge> edges = new ArrayList<>();
//            for (String u : users) {
//                if (u == page.get_owner()) break;
//                Edge e = graph_model.factory().newEdge(uname_to_node.get(page.get_owner()), uname_to_node.get(u), true);
//                edges.add(e);
//            }
//
//            // create graph
//            DirectedGraph social_graph = graph_model.getDirectedGraph();
//            social_graph.addAllNodes(nodes);
//            social_graph.addAllEdges(edges);
//            graphs.add(social_graph);
////            HashSet<String> properties = thread.extract_properties();
////            HashSet<String> items = thread.extract_items();
//        }
//    }
//
//    public void parse() {
//        build_graph();
//    }
//
//    public ArrayList<DirectedGraph> get_social_graphs() {
//        return graphs;
//    }
//}
