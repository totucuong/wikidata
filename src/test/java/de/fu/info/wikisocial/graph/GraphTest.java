package de.fu.info.wikisocial.graph;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by totucuong-standard on 9/16/16.
 */
public class GraphTest {
    Graph g1;
    Graph g2;

    @Before
    public void setUp() throws Exception {
        g1 = new Graph(100);
        g1.addEdge(0,3);
        g1.addEdge(4,3);

        BufferedReader reader = new BufferedReader(new FileReader("./data/graph.txt"));
        g2 = new Graph(reader);
    }

    @Test
    public void v() throws Exception {
        assertEquals(100, g1.V());
    }

    @Test
    public void v2() throws Exception {
        assertEquals(5, g2.V());
    }

    @Test
    public void e() throws Exception {
        assertEquals(2, g1.E());
    }

    @Test
    public void e2() throws Exception {
        assertEquals(7, g2.E());
    }

    @Test
    public void adj() throws Exception {
        Iterable<Integer> vertices = g1.adj(3);
        Iterator<Integer> it = vertices.iterator();
        assertEquals(0, it.next().intValue());
        assertEquals(4, it.next().intValue());
    }

    @Test
    public void adj2() throws Exception {
        Iterable<Integer> vertices = g2.adj(3);
        Iterator<Integer> it = vertices.iterator();
        assertEquals(2, it.next().intValue());
        assertEquals(4, it.next().intValue());
        assertEquals(1, it.next().intValue());
    }
}