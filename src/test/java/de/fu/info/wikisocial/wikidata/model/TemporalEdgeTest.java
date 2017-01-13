package de.fu.info.wikisocial.wikidata.model;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by totucuong-standard on 1/13/17.
 */
public class TemporalEdgeTest {

    private TemporalEdge edge1;
    private TemporalEdge edge2;

    @Test
    public void compare_two_date() {
        assertEquals(-1, edge1.compareTo(edge2));
    }

    @Test
    public void sort_edge_according_to_date() {
        ArrayList<TemporalEdge> edges = new ArrayList<>();
        edges.add(edge2);
        edges.add(edge1);
        Collections.sort(edges);
        assertEquals(edge1, edges.get(0));
    }

    @Before
    public void setUp() throws Exception {
        edge1 = new TemporalEdge("alex", "malice", new Date("2 January 2017"));
        edge2 = new TemporalEdge("cuong", "alex", new Date("22 March 2107"));
    }
}