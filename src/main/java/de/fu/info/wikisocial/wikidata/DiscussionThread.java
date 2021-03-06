package de.fu.info.wikisocial.wikidata;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by totucuong on 8/4/16.
 * This class model a discussion thread on an user talk page.
 * @NotThreadSafe
 */
public class DiscussionThread {
    // owner of the talk page
    private String owner;

    // participants in the current thread
    private HashSet<String> users;

    // items mentioned by  current thread
    private HashSet<String> items;

    // properties mentioned by current thread
    private HashSet<String> properties;

    // text representation of current thread
    private String content;

    // regular expression for items, e.g., "Q3190566"
    private static final Pattern i_p = Pattern.compile("Q\\d+");

    // regular expression for properties
    private static final Pattern p_reg = Pattern.compile("P\\d+");

    // regular expression for users, e.g., "Taketa (talk) 10:09, 19 January 2015 (UTC)"
    // @TODO currently empty or hebrew user name is not extracted properly
//    private static final Pattern u_reg = Pattern.compile("(\\w+)\\s+\\(?talk\\)?\\s(\\d+:\\d+),\\s(\\d+)\\s(\\w+)\\s\\d{4}");
    private static final Pattern u_reg = Pattern.compile("(\\w+)\\s*\\(?(talk)?\\)?\\s*(\\d+:\\d+),\\s(\\d+)\\s(\\w+)\\s\\d{4}");

    public DiscussionThread(String content, String owner) {
        this.content = content;
        this.owner = owner;
        this.items = null;
        this.properties = null;
        this.users = null;
        extract();
    }

    private void extract () {
        extract_items();
        extract_properties();
        extract_users();
    }

    /**
     *
     * @return a list of Wikidata items
     */
    public HashSet<String> extract_items() {
        if (this.items == null) {
            this.items = new HashSet<String>();
            Matcher m = i_p.matcher(content);
            while (m.find()) {
                items.add(m.group());
            }
        }
        return new HashSet<>(items);
    }

    /**
     *
     * @return a list of Wikidata properties
     */
    public HashSet<String> extract_properties() {
        if (this.properties == null) {
            this.properties = new HashSet<String>();
            Matcher m = p_reg.matcher(content);
            while (m.find()) {
                properties.add(m.group());
            }
        }
        return new HashSet<>(properties);
    }

    /**
     *
     * @return a list of Wikidata users
     */
    public HashSet<String> extract_users() {
        if (this.users == null) {
            this.users = new HashSet<String>();
            Matcher m = u_reg.matcher(content);
            while (m.find()) {
                users.add(m.group(1));
            }
        }
        return new HashSet<>(users);
    }

    public String get_content() {
        return content;
    }

    public String get_owner() {
        return owner;
    }
}
