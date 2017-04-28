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
        if (args.length < 2) {
            System.out.println("Usage: java de.fu.info.wikisocial.executables.WTPNetworkGenerator" +
                    " <own-toc-list-file-path> <output-file-path>");
        } else {
            // read in users with talk page that has table of content
            List<User> users = Util.getUsers(args[0]);

            // extract wtp network
            WtpNetworkExtractor wtpNetworkExtractor = new WtpNetworkExtractor(args[1]);
            try {
                wtpNetworkExtractor.extract(users);
                wtpNetworkExtractor.save();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
