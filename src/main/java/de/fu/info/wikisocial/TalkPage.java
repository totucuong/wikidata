package de.fu.info.wikisocial;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by totucuong on 8/2/16.
 */
public class TalkPage {
    private URL source;
    private Document doc;

    public TalkPage(String talk_page) throws MalformedURLException{
        this.source = new URL(talk_page);
        try {
            doc = Jsoup.parse(source, 1000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String> get_threads() {
        ArrayList<String> threads = new ArrayList<String>();
        // fill in code here
        return threads;
    }

}
