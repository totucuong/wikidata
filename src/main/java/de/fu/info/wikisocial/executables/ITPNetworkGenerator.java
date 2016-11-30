package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.wikidata.extractor.ITPNetworkExtractor;
import de.fu.info.wikisocial.wikidata.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by totucuong-standard on 11/9/16.
 */
public class ITPNetworkGenerator {
    public static void main(String[] args) {
        // read in users with talk page that has table of content
        List<User> users = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("data/owners_toc.txt"))) {
            String cur;
            while  ((cur = in.readLine()) != null) {
                User u = new User();
                u.setUser_name(cur.replace(" ", "_"));
                users.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // extract wtp network
        ITPNetworkExtractor itpNetworkExtractor = new ITPNetworkExtractor("./data/itpnetwork.csv");
        try {
            itpNetworkExtractor.extract(users);
            itpNetworkExtractor.save();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
