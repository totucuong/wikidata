package de.fu.info.wikisocial.wikidata.model;


import java.util.List;

/**
 * Created by totucuong-standard on 9/19/16.
 * This class implements the Thread API. This corresponds to de.fu.info.wikisocial.DiscussionThread, but here we model
 * a discussion thread in Wikidata as a tree.
 */
public class Thread {
    // title of a discussion thread
    private String title;

    private Reply reply;


    public Thread(String title, Reply reply) {
        this.title = title;
        this.reply = reply;
    }

    public String getTitle() {
        return title;
    }

    public Reply getReply() {
        return reply;
    }
}
