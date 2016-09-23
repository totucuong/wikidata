package de.fu.info.wikisocial.wikidata.model;

import org.jsoup.nodes.Element;

/**
 * Created by totucuong-standard on 9/22/16.
 */
public class Reply {
    // body of reply
    private Element body;

    // original poster of the first message, i.e., the user who starts the discussion
    private String poster;

    public Reply(Element body, String poster) {
        this.body = body;
        this.poster = poster;
    }

    public Element getBody() {
        return body;
    }

    public String getPoster() {
        return poster;
    }
}
