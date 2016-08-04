package de.fu.info.wikisocial;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by totucuong on 8/2/16.
 */
public class TalkPage {
    // main talk page
    private Document doc;

    private ArrayList<String> threads;

    // table of content
    private Element toc;

    public TalkPage(URL talk_page) {
        try {
            doc = Jsoup.parse(talk_page, 1000);
            threads = null;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Document get_content() {
        return doc;
    }

    public ArrayList<String> get_threads() {
        if (threads == null)
            threads = find_threads();
        return threads;
    }

    private Element find_toc() {
        return doc.select("div.toc").first();
    }

    /**
     *
     * @return a list of threads. Each thread is represented as a String.
     */
    private ArrayList<String> find_threads() {
        ArrayList<String> threads = new ArrayList<String>();

        // get anchors
        ArrayList<String> anchors = find_anchors();

        // get content of each threads
        for (String anchor : anchors) {
            threads.add(get_discussion(anchor));
        }
        return threads;
    }

    public ArrayList<String> find_anchors() {
        ArrayList<String> anchors = new ArrayList<String>();

        // table of content
        Element toc = find_toc();

        // get anchors
        Elements links = toc.select("a");
        for (Element l : links) {
            anchors.add(l.attr("href"));
        }
        return anchors;
    }

    private String get_discussion(String anchor) {
        StringBuilder discussion_builder = new StringBuilder();
        Element thread_header = doc.getElementById(anchor.substring(1)).parent();
        Element next_sibling = thread_header.nextElementSibling();
        while (next_sibling != null && next_sibling.tagName() != "h2") {
            discussion_builder.append(next_sibling.text());
            discussion_builder.append(" ");
            next_sibling = next_sibling.nextElementSibling();
        }
        return discussion_builder.toString().trim();
    }

}
