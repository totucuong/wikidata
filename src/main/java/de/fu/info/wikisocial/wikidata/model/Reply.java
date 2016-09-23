package de.fu.info.wikisocial.wikidata.model;

import org.jsoup.nodes.Element;

/**
 * Created by totucuong-standard on 9/22/16.
 */
public class Reply {
    // question that start the reply
    private String question;

    // the answer to the question (a <dl></dl> group)
    private Element answer;

    public Reply(String question, Element answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public Element getAnswer() {
        return answer;
    }
}
