package de.fu.info.wikisocial.wikidata.model;

/**
 * Created by totucuong-standard on 10/20/16.
 */
public class ItemDiscussionPage {
    private String html;
    private String item_id;

    public ItemDiscussionPage(String html, String item_id) {
        this.html = html;
        this.item_id = item_id;
    }

    public String getHtml() {
        return html;
    }

    public String getItem_id() {
        return item_id;
    }
}
