package de.fu.info.wikisocial;

import de.fu.info.wikisocial.wikidata.TalkPage;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by totucuong on 8/3/16.
 */
public class TalkPageTest {
    private TalkPage talk_page, talk_page_with_archive;


    @Before
    public  void setUp() throws  Exception {
        talk_page = new TalkPage(new URL("https://www.wikidata.org/wiki/User_talk:Taketa"), "Taketa");
        talk_page_with_archive = new TalkPage(new URL("https://www.wikidata.org/wiki/User_talk:Multichill"), "Multichill");
    }

    @Test
    public void testGet_talk_page() throws Exception {
        Document doc = talk_page.get_content();
    }


    @Test
    public void testFind_anchors_size() throws Exception {
        ArrayList<String> anchors = talk_page.find_anchors();
        assertEquals(14, anchors.size());
    }

    @Test
    public void testFind_anchors_content() throws Exception {
        ArrayList<String> anchors = talk_page.find_anchors();
        assertEquals("#Nomination", anchors.get(2));
    }

    @Test
    public void testGet_threads() throws Exception {
        ArrayList<String> threads = talk_page.get_threads();
        assertEquals(14, threads.size());
        assertEquals("I checked 2000 edits before/after it. I can't find the same problem. and I can't " +
                "find error in statements or categories related to this. Maybe a bug in AutoList? If you find " +
                "this problem again please tell me.--GZWDer (talk) 09:41, 25 May 2014 (UTC)", threads.get(1));
//        for (String t : threads) {
//            System.out.println(t);
//            System.out.println("======================================================================================");
//        }
    }

    @Test
    public void testGet_archive_links() throws Exception {
        ArrayList<URL> links = talk_page_with_archive.get_archive_links();
        assertEquals(45, links.size());
    }
}