package de.fu.info.wikisocial;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by totucuong on 8/2/16.
 */
public class TalkPage {
    private Document doc;

    public TalkPage(URL talk_page) {
        try {
            doc = Jsoup.parse(talk_page, 1000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Document get_content() {
        return doc;
    }

    public ArrayList<String> get_discussion_threads() {
        ArrayList<String> threads = new ArrayList<String>();
        // fill in code here
        return threads;
    }

}
