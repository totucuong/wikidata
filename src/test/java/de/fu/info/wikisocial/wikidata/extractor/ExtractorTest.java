package de.fu.info.wikisocial.wikidata.extractor;

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
}