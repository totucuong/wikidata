package de.fu.info.wikisocial.wikidata.model;

import de.fu.info.wikisocial.graph.Graph;

import java.util.ArrayList;

/**
 * Created by totucuong-standard on 1/13/17.
 * This is a collection of weekly graphs. Typical methods is
 * get_graph(int week_index)
 * number_of_weeks()
 */
public class TemporalGraphs {

    private ArrayList<TemporalEdge> edges;

    public TemporalGraphs(String filepath) {

    }

    public Graph get_graph(int week) {
        return null;
    }

    /**
     *
     * @return the number weeks in the collection of graphs.
     * For example, if we have G1, G2, G3, G4 then the method return 4.
     */
    public int number_of_weeks() {
        return 0;
    }
}
