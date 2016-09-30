package de.fu.info.wikisocial.wikidata.extractor;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;

import de.fu.info.wikisocial.wikidata.model.Thread;

import static org.junit.Assert.*;

/**
 * Created by totucuong-standard on 9/26/16.
 */
public class TalkPageExtractorTest {
    private TalkPageExtractor pageExtractor;

    @Before
    public void setUp() throws Exception {
        pageExtractor = new TalkPageExtractor(new URL("https://www.wikidata.org/wiki/User_talk:Multichill/Archives/2014/May"),
                "Multichill");
    }

    @Test
    public void get_threads() throws Exception {
        ArrayList<Thread> threads = pageExtractor.get_threads();
        assertEquals(5, threads.size());
    }

    @Test
    public void right_title() throws  Exception {
        ArrayList<Thread> threads = pageExtractor.get_threads();
        assertEquals("odd error", threads.get(1).getTitle());
    }

}