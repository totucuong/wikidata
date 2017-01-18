package de.fu.info.wikisocial.wikidata.model;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.time.LocalDate;

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

    @Test
    public void days_in_between() {
        Date first = new Date("2 January 2012");
        Date last = new Date("12 January 2012");
        assertEquals(10, ChronoUnit.DAYS.between(first.toInstant(), last.toInstant()));
    }

    @Before
    public void setUp() throws Exception {
        edge1 = new TemporalEdge("alex", "malice", LocalDate.parse("2 January 2017", DateTimeFormatter.ofPattern("d[d] MMMM yyyy")));
        edge2 = new TemporalEdge("cuong", "alex", LocalDate.parse("22 March 2107",  DateTimeFormatter.ofPattern("d[d] MMMM yyyy")));
    }

}