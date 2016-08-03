package de.fu.info.wikisocial;

import org.jsoup.nodes.Document;
import org.junit.Test;

import java.net.URL;

/**
 * Created by totucuong on 8/3/16.
 */
public class TalkPageTest {

    @Test
    public void testGet_talk_page() throws Exception {
        TalkPage talk_page = new TalkPage(new URL("https://www.wikidata.org/wiki/User_talk:Taketa"));
        Document doc = talk_page.get_content();
        System.out.println(doc.text());
    }

    @Test
    public void testGet_discussion_threads() throws Exception {

    }
}