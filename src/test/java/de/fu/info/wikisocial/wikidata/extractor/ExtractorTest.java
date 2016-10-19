package de.fu.info.wikisocial.wikidata.extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by totucuong-standard on 9/22/16.
 */
public class ExtractorTest {
    @Test
    public void extract_user() throws Exception {
        String msg = "Import blank location map as item image ([1]) really does not make any sense." +
                " Also adding \"imported from xxwiki\" as reference makes only a little sense (in this" +
                " case just consider, if it is really needed). --Jklamo (talk) 07:31, 30 April 2014 (UTC)";
        assertEquals("Jklamo", Extractor.extract_user(msg));

    }

    @Test
    public void extract_user2() throws Exception {
        String msg = "Q4918 was created as a duplicate of it, but with a lot more entries. " +
                "As there is no merge currently, I deleted the one with less links, and moved them over. " +
                "See conversation above Reedy (talk) 16:05, 1 November 2012 (UTC)";
        assertEquals("Reedy", Extractor.extract_user(msg));

    }

    @Test
    public void extract_user3() throws Exception {
        String msg = "BTW: you could also use Special:AllMessages?prefix=MainPage&filter=all&lang=en-gb" +
                " to get the content of the messages. Helder 22:52, 3 November 2012 (UTC)";
        assertEquals("Helder", Extractor.extract_user(msg));
    }


    @Test
    public void extract_user_from_html() throws  Exception {
        String html = "<p>specially when removing sitelinks. <a class=\"external text\" href=\"http://www.wikidata.org" +
                "/w/index.php?title=User_talk%3AAndreasmperu&amp;diff=48620792&amp;oldid=48352515\">Further explanation " +
                "here</a>. <a href=\"/wiki/User:Andreasmperu\" title=\"User:Andreasmperu\"><font color=\"black\">" +
                "Andreasm</font></a> <sup><a href=\"/wiki/User_talk:Andreasmperu\" title=\"User talk:Andreasmperu\"" +
                "><font color=\"green\">háblame / just talk to me</font></a></sup> 04:37, 31 May 2013 (UTC)</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        Element msg = doc.body();
        assertEquals("Andreasm", Extractor.extract_user(msg));
    }

    @Test
    public void extract_user_from_html2() throws Exception {
        String html = "<p>I presume <a href=\"https://cs.wikipedia.org/wiki/Muflon\" class=\"extiw\" title=\"cs:Muflon\">" +
                "cs:Muflon</a> should be linked to <a href=\"/wiki/Q993274\" title=\"Q993274\">Q993274</a> because this" +
                " article describes european muflon (Ovis musimon = Ovis orientalis musimon). " +
                "The article about asiatic mouflon or mouflon in wide sense (Ovis orientalis = Ovis gmelini) " +
                "currently does not exist in cs-wiki. -- <a href=\"/w/index.php?title=User:Alexander_Shatulin&amp;" +
                "action=edit&amp;redlink=1\" class=\"new\" title=\"User:Alexander Shatulin (page does not exist)\">" +
                "Alexander Shatulin</a> (<a href=\"/wiki/User_talk:Alexander_Shatulin\" title=\"User talk:Alexander " +
                "Shatulin\">talk</a>) 10:47, 13 March 2013 (UTC)</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        Element msg = doc.body();
        assertEquals("Alexander_Shatulin", Extractor.extract_user(msg));
    }
}