package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.wikidata.model.TemporalGraphs;

/**
 * Created by totucuong-standard on 1/20/17.
 */
public class ContentNetworkSeriesGenerator {
    public static void main(String[] args) {
        TemporalGraphs cGraphs = new TemporalGraphs("data/content-networks/contentnetwork.csv");
        cGraphs.read();
        cGraphs.split_weekly();
        cGraphs.save();
    }
}
