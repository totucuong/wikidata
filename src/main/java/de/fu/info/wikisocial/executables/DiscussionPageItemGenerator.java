package de.fu.info.wikisocial.executables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.fu.info.wikisocial.wikidata.DiscussionThread;
import de.fu.info.wikisocial.wikidata.model.ItemDiscussionPage;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by totucuong-standard on 10/20/16.
 * This program collects discuss page of Wikidata's items in raw html
 */
public class DiscussionPageItemGenerator {

    private final String item_dis_page_prefix = "https://www.wikidata.org/wiki/Talk:";
    private String items_file;
    private List<ItemDiscussionPage> pages;

    public DiscussionPageItemGenerator(String items_file) {
        this.items_file = items_file;
        pages = new ArrayList<>();
    }

    public void run()  throws Exception {
        int count = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(items_file))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (count % 50 == 0)
                    System.out.println("DEBUG - processing page of item numbered" + count);
                URL url = new URL(item_dis_page_prefix + line);
                Document doc;
                try {
                    doc = Jsoup.parse(url, 10000);
                }
                catch (HttpStatusException hsex) {
                    continue;
                }
                ItemDiscussionPage page = new ItemDiscussionPage(doc.html(), line);
                pages.add(page);
                count++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        save();
    }

    private void save() throws Exception {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try (PrintWriter printWriter = new PrintWriter("data/raw/items_discussion.json")) {
            for (ItemDiscussionPage p : pages) {
                printWriter.println(gson.toJson(p));
            }
        }
    }


    public static void main(String[] args) throws Exception {
        DiscussionPageItemGenerator gen = new DiscussionPageItemGenerator("data/items_with_discussion.csv");
        gen.run();
    }

}
