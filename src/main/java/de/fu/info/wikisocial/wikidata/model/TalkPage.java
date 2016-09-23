package de.fu.info.wikisocial.wikidata.model;

import de.fu.info.wikisocial.crawler2.NoTocException;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by totucuong on 8/2/16.
 * This class model a single web page that has a table content. Each Wikidata user has a user talk page which have a
 * table of content and it may also have an archive navigation bar which poits to archived talk pages. Each of archive
 * talk page also has a table of contents.
 */
public class TalkPage {
    // main talk page
    private Document doc;

    // owner of current talk page
    private String owner;

    private ArrayList<String> threads;

    public TalkPage(URL talk_page, String owner) {
        try {
            this.owner = owner;
            doc = Jsoup.parse(talk_page, 1000);
            threads = null;
        } catch (Exception ex) {
//            do nothing
//            ex.printStackTrace();
        }
    }

    public Document get_content() {
        return doc;
    }

    public String get_owner() {
        return owner;
    }

    public ArrayList<String> get_threads() {
        if (threads == null) {
            try {
                threads = find_threads();
            } catch (NoTocException nex) {
                System.out.println(nex.getMessage());
            }
        }
        return threads;
    }

    public ArrayList<Thread> get_threads2() {
        //@TODO Implement
        return null;
    }

    private Element find_toc() {
        if (doc != null)
            return doc.select("div.toc").first();
        return null;
    }

    /**
     *
     * @return a list of threads. Each thread is represented as a String.
     */
    private ArrayList<String> find_threads() throws NoTocException {
        ArrayList<String> threads = new ArrayList<String>();

        // get anchors
        try {
            ArrayList<String> anchors = find_anchors();
            // get content of each threads
            for (String anchor : anchors) {
                threads.add(get_discussion(anchor));
            }
        } catch (NoTocException nex) {
            throw nex;
        }
        return threads;
    }

    /**
     *
     * @return a list of anchors of threads
     */
    public ArrayList<String> find_anchors() throws NoTocException {
        ArrayList<String> anchors = new ArrayList<String>();

        // table of content
        Element toc = find_toc();

        if (toc == null)
            throw new NoTocException("There is not table of content on " + this.owner + " 's talk page");
        // get anchors
        Elements links = toc.select("a");
        for (Element l : links) {
            anchors.add(l.attr("href"));
        }
        return anchors;
    }

    /**
     *
     * @param anchor of a thread
     * @return a String representation of a thread
     */
    private String get_discussion(String anchor) {
        StringBuilder discussion_builder = new StringBuilder();
        if (doc.getElementById(anchor.substring(1)) != null) {
            Element thread_header = doc.getElementById(anchor.substring(1)).parent();
            Element next_sibling = thread_header.nextElementSibling();
            while (next_sibling != null && next_sibling.tagName() != "h2") {
                discussion_builder.append(next_sibling.text());
                discussion_builder.append(" ");
                next_sibling = next_sibling.nextElementSibling();
            }
        }
        return discussion_builder.toString().trim();
    }

    /**
     *
     * @return the archive table of talk pages for the current user
     */
    private Element get_archive_table() {
        return doc.select("table.infobox.plainlinks.wikitable").get(0);
    }

    /**
     *
     * @return a list of links of archived talks
     */
    public ArrayList<URL> get_archive_links() {
        ArrayList<URL> links = new ArrayList<>();
        Elements e_links = get_archive_table().select("a");
        for (int i = 1; i < e_links.size(); i++) {
            try {
                links.add(new URL(e_links.get(i).attr("abs:href")));
            } catch (MalformedURLException ex) {
                // do nothing
            }
        }
        return links;
    }
}
