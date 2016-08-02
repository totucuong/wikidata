package de.fu.info.wikisocial;

import java.net.URL;

/**
 * Created by totucuong on 8/2/16.
 */
public class User {
    private int id;
    private String user_name;
    private URL talk_page;
    private int edit_count;

    public User() {
        id = -1;
        user_name = "";
        talk_page = null;
        edit_count = -1;
    }

    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public URL getTalk_page() {
        return talk_page;
    }

    public int getEdit_count() {
        return edit_count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setTalk_page(URL talk_page) {
        this.talk_page = talk_page;
    }

    public void setEdit_count(int edit_count) {
        this.edit_count = edit_count;
    }
}
