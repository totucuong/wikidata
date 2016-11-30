package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.wikidata.extractor.WithinTalkPagePropertySpaceExtractor;

/**
 * Created by totucuong-standard on 11/30/16.
 */
public class WithinTalkPageNetworkPropertySpaceGenerator {

    public static void main(String[] args) {
        WithinTalkPagePropertySpaceExtractor extractor = new WithinTalkPagePropertySpaceExtractor("./data/raw/props_discussion.json", "./data/wtpnetwork_propertynamespace.csv");
        extractor.generate();
        extractor.save();
    }
}
