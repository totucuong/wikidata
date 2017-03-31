package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.wikidata.extractor.ContentNetworkExtractor;
import de.fu.info.wikisocial.wikidata.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by totucuong-standard on 11/23/16.
 */
public class ContentNetworkGenerator {
    public static void main(String[] args) {
        // read in users with talk page that has table of content
        List<User> users = Util.getUsers("data/owners_toc.txt");

        ContentNetworkExtractor contentNetworkExtractor = new ContentNetworkExtractor("./data/content-networks/contentnetwork.csv");
        try {
            contentNetworkExtractor.extract(users);
            contentNetworkExtractor.save();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
