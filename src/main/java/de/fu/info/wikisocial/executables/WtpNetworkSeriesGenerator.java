package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.wikidata.model.TemporalGraphs;

/**
 * Created by totucuong-standard on 1/20/17.
 * This program generate wtp-network series
 */
public class WtpNetworkSeriesGenerator {
    public static void main(String[] args) {
        TemporalGraphs wtpGraphs = new TemporalGraphs("data/wtp-networks/wtpnetwork_timestamp.csv");
        wtpGraphs.read();
        wtpGraphs.split_weekly();
        wtpGraphs.save();
    }
}
