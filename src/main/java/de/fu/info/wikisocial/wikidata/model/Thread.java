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

    // first message
    private String first_msg;

    // replies messages
    private List<Reply> replies;


    public Thread(String title, String first_msg, List<Reply> replies) {
        this.title = title;
        this.first_msg = first_msg;
        this.replies = replies;
    }

    public String getTitle() {
        return title;
    }

    public String getFirst_msg() {
        return first_msg;
    }

    public List<Reply> getReplies() {
        return replies;
    }
}
