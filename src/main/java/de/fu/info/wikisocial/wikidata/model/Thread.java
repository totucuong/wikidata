package de.fu.info.wikisocial.wikidata.model;


import java.util.List;

/**
 * Created by totucuong-standard on 9/19/16.
 * This class implements the Thread API. This corresponds to de.fu.info.wikisocial.DiscussionThread, but here we model
 * a discussion thread in Wikidata as a tree.
 * A discussion thread in a wiki page is model by
 *      1. its title, which is represented by <h2></h2> tag
 *      2. its reply, which is represented by class Reply
 */
public class Thread {
    // title of a discussion thread
    private String title;

    private Reply reply;


    public Thread(String title, Reply reply) {
        if (title == null || reply == null)
            throw (new IllegalArgumentException("Title and Reply cannot be null"));
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
