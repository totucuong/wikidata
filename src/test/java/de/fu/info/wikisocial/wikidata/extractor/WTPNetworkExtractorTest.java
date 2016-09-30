package de.fu.info.wikisocial.wikidata.extractor;

import de.fu.info.wikisocial.wikidata.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by totucuong-standard on 9/26/16.
 */
public class WTPNetworkExtractorTest {
    private List<User> users;

    @Before
    public void setUp() throws Exception {
        UserFileReader userFileReader = new UserFileReader("./data/users_list_sample.csv");
        users = userFileReader.getUsers();
    }

    @Test
    public void extract() throws Exception {
        WTPNetworkExtractor wtpNetworkExtractor = new WTPNetworkExtractor("./data/wtpnetwork.txt");
        wtpNetworkExtractor.extract(users);
        wtpNetworkExtractor.save();
    }

//    @Test
//    public void save() throws Exception {
//
//    }

}