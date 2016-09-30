package de.fu.info.wikisocial;

import de.fu.info.wikisocial.wikidata.model.User;
import de.fu.info.wikisocial.wikidata.extractor.UserFileReader;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by totucuong on 8/3/16.
 */
public class UserFileReaderTest {

    @org.junit.Test
    public void testGetUsers() throws Exception {
//        String current = new java.io.File(".").getCanonicalPath();
//        System.out.println(current);
        UserFileReader userFileReader = new UserFileReader("./data/wd_users_10edits_2015-03-05.csv");
        ArrayList<User> users = userFileReader.getUsers();
        assertEquals(22450, users.size());
        assertEquals("Drakosh", users.get(5295).getUser_name());
        assertEquals("90668", users.get(5295).getUser_id());
        assertEquals(54, users.get(5295).getEdit_count());

    }
}