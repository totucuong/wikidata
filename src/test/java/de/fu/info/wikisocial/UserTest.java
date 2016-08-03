package de.fu.info.wikisocial;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by totucuong on 8/3/16.
 */
public class UserTest {

    @Test
    public void testGetTalk_page() throws Exception {
        User user = new User();
        user.setEdit_count(20379);
        user.setUser_name("Taketa");
        user.setUser_id("22450");
        assertEquals("https://www.wikidata.org/wiki/User_talk:Taketa", user.getTalk_page().toString());
    }
}