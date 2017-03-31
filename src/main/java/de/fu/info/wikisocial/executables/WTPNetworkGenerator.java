package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.wikidata.extractor.WtpNetworkExtractor;
import de.fu.info.wikisocial.wikidata.model.User;

import java.util.List;

/**
 * Created by totucuong-standard on 10/19/16.
 * This program generate within-talk-page
 */
public class WTPNetworkGenerator {

    public static void main(String[] args) {
        // read in users with talk page that has table of content
        List<User> users = Util.getUsers("data/owners_toc.txt");

        // extract wtp network
        WtpNetworkExtractor wtpNetworkExtractor = new WtpNetworkExtractor("./data/wtp-networks/wtpnetwork_timestamp.csv");
        try {
            wtpNetworkExtractor.extract(users);
            wtpNetworkExtractor.save();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
