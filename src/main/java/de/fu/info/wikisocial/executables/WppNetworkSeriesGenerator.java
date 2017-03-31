package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.wikidata.model.TemporalGraphs;

/**
 * Created by totucuong-standard on 1/20/17.
 */
public class WppNetworkSeriesGenerator {
    public static void main(String[] args) {
        TemporalGraphs wppGraphs = new TemporalGraphs("data/wpp-networks/wppnetwork.csv");
        wppGraphs.read();
        wppGraphs.split_weekly();
        wppGraphs.save();
    }
}
