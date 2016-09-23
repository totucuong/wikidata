package de.fu.info.wikisocial.wikidata.extractor;

import de.fu.info.wikisocial.wikidata.model.Reply;
import de.fu.info.wikisocial.wikidata.model.Thread;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by totucuong-standard on 9/22/16.
 */
public class Extractor {

    // regular expression for users, e.g., "Taketa (talk) 10:09, 19 January 2015 (UTC)"
    // @TODO currently empty or hebrew user name is not extracted properly
    private static final Pattern u_reg = Pattern.compile("(\\w+)\\s*\\(?(talk)?\\)?\\s*(\\d+:\\d+),\\s(\\d+)\\s" +
            "(\\w+)\\s\\d{4}");

    /**
     * Extract an user name from a message text
     * @param msg a message
     * @return user name, or null if cannot find
     */
    public static String extract_user(String msg) {
        Matcher m = u_reg.matcher(msg);
        if (m.find()) {
            return (m.group(1));
        }
        return null;
    }

    /**
     *
     * @param elements contains 3 elements: <h2></h2> for the thread's title, <p></p> for the first msg, and, <dl></dl>
     *                 for a list of replies
     * @return a Thread object
     */
    public static Thread extract_thread(List<Element> elements) {
        String poster = Extractor.extract_user(elements.get(1).toString());
        List<Element> dd = new ArrayList<>();
        for (Element e : elements.get(2).children()) {
            if (e.tagName() == "dd")
                dd.add(e);
        }
        List<Reply> replies = new ArrayList<>();
        dd.forEach(r -> replies.add(new Reply(r, poster)));
        return new Thread(elements.get(0).toString(), elements.get(1).toString(), replies);
    }



    /**
     * Find the table of content of a Wikidata talk page
     * @param doc html document
     * @return table of content
     */
    public static Element find_toc(Document doc) {
        if (doc != null)
            return doc.select("div.toc").first();
        return null;
    }
}
