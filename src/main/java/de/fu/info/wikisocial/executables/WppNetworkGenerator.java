package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.wikidata.extractor.WppNetworkExtractor;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by totucuong-standard on 1/20/17.
 */
public class WppNetworkGenerator {

    public static void main(String[] args) throws IOException {
        // read in users with talk page that has table of content
        ArrayList<String> properties = Util.getProperties("data/props_with_discussion.csv");
        WppNetworkExtractor wppNetworkExtractor = new WppNetworkExtractor("./data/wpp-networks/wppnetwork.csv");
        wppNetworkExtractor.extract(properties);
        wppNetworkExtractor.save();
    }
}
