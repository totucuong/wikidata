package de.fu.info.wikisocial.wikidata.model;

import de.fu.info.wikisocial.wikidata.extractor.Extractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.ArrayList;

/**
 * Created by totucuong-standard on 9/22/16.
 * This class implements the Reply API.
 * A Reply consists of two elements:
 *      1. the question that starts the thread
 *      2. the answer to the question, which represented by a <dl></dl> group. This group may includes multiple users'
 *      replies,(<dd></dd>)s, to the question. Each user reply, again, can be itself a discussion thread.
 */
public class Reply {
    // question that start the reply in html format
    private String question;

    // the answer to the question (a <dl></dl> group)
    private Element answer;

    public Reply(String question, Element answer) {
        if (question == null)
            throw (new IllegalArgumentException("Question of a Reply cannot be null"));
        this.question = question;
        this.answer = answer;
    }

    public String get_poster() {
        Document doc = Jsoup.parseBodyFragment(question);
        Element body = doc.body();
        return Extractor.extract_user(body);
    }

    /**
     *
     * @return a list of replies, null if there is no replies
     */
    public ArrayList<Reply> get_replies() {
        if (answer == null)
            return null;
        ArrayList<Reply> replies = new ArrayList<>();
        for (Element e : answer.children()) {
            if (e.tagName() == "dd") {
                // get answer
                Element answer = null;
                StringBuilder questionBuilder = new StringBuilder();
                for (Node n : e.childNodes()) {
                    if (n instanceof Element && ((Element) n).tagName() == "dl") {
                        answer = (Element) n;
                        break;
                    } else {
                        questionBuilder.append(n.toString());
                    }
                }
                replies.add(new Reply(questionBuilder.toString(), answer));
            }
        }
        return replies;
    }

//    /**
//     *
//     * @return a list of replies, null if there is no replies
//     */
//    public ArrayList<Reply> get_replies() {
//        if (answer == null)
//            return null;
//        ArrayList<Reply> replies = new ArrayList<>();
//        for (Element e : answer.children()) {
//            if (e.tagName() == "dd") {
//                // get answer
//                Element answer = null;
//                StringBuilder questionBuilder = new StringBuilder();
//                for (Node n : e.childNodes()) {
//                    if (n instanceof Element && ((Element) n).tagName() == "dl") {
//                        answer = (Element) n;
//                        break;
//                    } else if (n instanceof TextNode) {
//                        questionBuilder.append(((TextNode) n).text());
//                    } else if (n instanceof Element) {
//                        questionBuilder.append(((Element) n).text());
//                    }
//                }
//                replies.add(new Reply(questionBuilder.toString(), answer));
//            }
//        }
//        return replies;
//    }
}
