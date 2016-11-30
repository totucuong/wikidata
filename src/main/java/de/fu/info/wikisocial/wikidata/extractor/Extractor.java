package de.fu.info.wikisocial.wikidata.extractor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private static final Pattern i_reg = Pattern.compile("[Q,q]\\d+");
    private static final Pattern p_reg = Pattern.compile("[P,p]\\d+");

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
     * Extract user ids from a textual snippets
     * @param content a string
     * @return a set of user ids, the size of the set is 0 if none users found
     */
    public static Set<String> extract_users(String content) {
        HashSet<String> users = new HashSet<>();
        Matcher m = u_reg.matcher(content);
        while (m.find()) {
            users.add(m.group(1));
        }
        return users;
    }

    public static String extract_user(Element msg) {
        // first look for link to user page
        Elements user_page_link = msg.select("[href*=/wiki/User:]");
        for (Element e : user_page_link) {
            return e.text();
        }

        // if no user page look for talk page
        Elements user_talk_page_link = msg.select("[href*=/wiki/User_talk]");
        for (Element e : user_talk_page_link) {
            return e.attr("href").substring(16);
        }

        // if no user page no talk page use regex pattern
        return Extractor.extract_user(msg.text());
    }

    /**
     * Compute the number of messages in a discussion. The number of messages is equal to the number of user signature,
     * i.e., user_id (talk) timestamp.
     * @param content the discussion content
     * @return number of msg
     */
    public static int count_num_msgs(String content) {
        int count = 0;
        Matcher m = u_reg.matcher(content);
        while (m.find()) {
            count++;
        }
        return count;
    }

    public static Set<String> extract_props(String content) {
        HashSet<String> props = new HashSet<>();
        Matcher m = p_reg.matcher(content);
        while (m.find()) {
            props.add(m.group(0));
        }
        return props;
    }

    public static int count_num_prop_mentions(String content) {
        int count = 0;
        Matcher m = p_reg.matcher(content);
        while (m.find()) {
            count++;
        }
        return count;
    }

    public static Set<String> extract_items(String content) {
        HashSet<String> items = new HashSet<>();
        Matcher m = i_reg.matcher(content);
        while (m.find()) {
            items.add(m.group(0));
        }
        return items;
    }


    public static Set<String> extract_artifacts(String content) {
        HashSet<String> artifacts = new HashSet<>();
        artifacts.addAll(extract_items(content));
        artifacts.addAll(extract_props(content));
        return artifacts;
    }

    public static int count_num_item_mentions(String content) {
        int count = 0;
        Matcher m = i_reg.matcher(content);
        while (m.find()) {
//            System.out.println("DEBUG - " + m.group(0));
            count++;
        }
        return count;
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
