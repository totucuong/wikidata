package de.fu.info.wikisocial;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by totucuong on 8/2/16.
 */
public class TalkPage {
    // main talk page
    private Document doc;

    // table of content
    private Element toc;

    public TalkPage(URL talk_page) {
        try {
            doc = Jsoup.parse(talk_page, 1000);
            toc = find_toc();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Document get_content() {
        return doc;
    }

    private Element find_toc() {
        return doc.select("div.toc").first();
    }

    // @TODO: continue writing this function
//    private ArrayList<Element> find_threads() {
//        Elements links = toc.select("a");
//    }

    public ArrayList<String> get_discussion_threads() {
        ArrayList<String> threads = new ArrayList<String>();
        // fill in code here
        return threads;
    }

}
