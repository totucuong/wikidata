package de.fu.info.wikisocial.wikidata.networks;

import de.fu.info.wikisocial.wikidata.extractor.TalkPageExtractor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by totucuong-standard on 9/19/16.
 */
public class WTPNetworkBuilder {
    /**
     *
     * @param pages list of talk pages
     * @param filename output file to store within-network-page graph
     * @return a WTPNetwork constructed from pages
     */
    public static void build(ArrayList<TalkPageExtractor> pages, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {


        }
    }
}
