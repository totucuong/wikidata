package de.fu.info.wikisocial.wikidata.extractor;

import de.fu.info.wikisocial.crawler2.NoTocException;
import de.fu.info.wikisocial.wikidata.model.Reply;
import de.fu.info.wikisocial.wikidata.model.Thread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by totucuong on 8/2/16.
 */
public class TalkPageExtractor {
    // main talk page
    private Document doc;

    // owner of current talk page
    private String owner;

    private ArrayList<Thread> threads;

    public TalkPageExtractor(URL talk_page, String owner) {
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

    public ArrayList<Thread> get_threads() {
        if (threads == null) {
            try {
                threads = find_threads();
            } catch (NoTocException nex) {
                System.out.println(nex.getMessage());
            }
        }
        return threads;
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
    private ArrayList<Thread> find_threads() throws NoTocException {
        ArrayList<Thread> threads = new ArrayList<>();

        // get anchors
        try {
            ArrayList<String> anchors = find_anchors();
            // get content of each threads
            for (String anchor : anchors) {
                threads.add(extract_thread(anchor));
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
     * Extract a Thread at postion provided by anchor
     * @param anchor of a thread
     * @return a Thread
     *
     * A thread in a Wikidata includes three parts:
     * 1. title (thread header)
     * 2. first message ( first <p></p>)
     * 3. thread's body ( first <dl></dl>)
     */
    private Thread extract_thread(String anchor) {
        if (doc.getElementById(anchor.substring(1)) != null) {
            // title
            Element thread_header = doc.getElementById(anchor.substring(1)).parent();
            String title = thread_header.text();

            // title and body
            String question = null;
            Element answer = null;
            boolean got_answer = false;
            boolean got_question = false;
            Element next = thread_header.nextElementSibling();
            do {
                if (next.tagName() == "p") {
                    question = next.text();
                    got_question = true;
                }
                if (next.tagName() == "dl") {
                    answer = next;
                    got_answer = true;
                }
                next = next.nextElementSibling();

                if (next.tagName() == "h2")
                    break;
            } while (!(got_answer && got_question));
            return new Thread(title, new Reply(question, answer));
        }
        return null;
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

    public static void main(String[] args) {
        try {
            TalkPageExtractor pageExtractor = new TalkPageExtractor(new URL("https://www.wikidata.org/wiki/User_talk:Taketa"), "Taketa");
            ArrayList<Thread> threads = pageExtractor.get_threads();
            for (Thread t: threads) {
                System.out.println(t.getTitle());
                System.out.println(t.getReply().getQuestion());
                System.out.println("========================");
            }
            if (threads.get(1).getReply().getAnswer() == null)
                System.out.println("null answer from " + threads.get(1).getTitle());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
