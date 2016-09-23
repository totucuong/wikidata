package de.fu.info.wikisocial;

import de.fu.info.wikisocial.wikidata.model.TalkPage;
import de.fu.info.wikisocial.wikidata.TalkPageGraph;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by totucuong on 8/9/16.
 */
public class TalkPageGraphTest {

    private TalkPageGraph talkPageGraph;

    @Before
    public void setUp() throws Exception {
        talkPageGraph = new TalkPageGraph(new TalkPage(new URL("https://www.wikidata.org/wiki/User_talk:Taketa"),
                "Taketa"));

    }

    @Test
    public void testParse() throws Exception {
        talkPageGraph.parse();
        assertEquals(14, talkPageGraph.get_social_graphs().size());
    }
}