package de.fu.info.wikisocial.wikidata.extractor;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by totucuong-standard on 9/22/16.
 */
public class UtilityTest {
    @Test
    public void extract_user() throws Exception {
        String msg = "Import blank location map as item image ([1]) really does not make any sense." +
                " Also adding \"imported from xxwiki\" as reference makes only a little sense (in this" +
                " case just consider, if it is really needed). --Jklamo (talk) 07:31, 30 April 2014 (UTC)";
        assertEquals("Jklamo", Extractor.extract_user(msg));

    }

}