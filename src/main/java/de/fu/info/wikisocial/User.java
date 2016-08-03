package de.fu.info.wikisocial;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by totucuong on 8/2/16.
 */
public class User {
    private String user_id;
    private String user_name;
    private URL talk_page;
    private int edit_count;
    private final String talk_page_prefix = "https://www.wikidata.org/wiki/User_talk:";

    public User() {
        user_id = "";
        user_name = "";
        talk_page = null;
        edit_count = -1;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public URL getTalk_page() {
        return talk_page;
    }

    private void construct_talk_page_url() {
        try {
            talk_page = new URL(talk_page_prefix + user_name);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    public int getEdit_count() {
        return edit_count;
    }

    public void setUser_id(String id) {
        this.user_id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        construct_talk_page_url();
    }

    public void setTalk_page(URL talk_page) {
        this.talk_page = talk_page;
    }

    public void setEdit_count(int edit_count) {
        this.edit_count = edit_count;
    }

}
