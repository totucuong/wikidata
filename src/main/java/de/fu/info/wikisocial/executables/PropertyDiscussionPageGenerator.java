package de.fu.info.wikisocial.executables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.fu.info.wikisocial.wikidata.model.ItemDiscussionPage;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by totucuong-standard on 10/28/16.
 * This program collects discuss page of Wikidata's items in raw html
 */
public class PropertyDiscussionPageGenerator {
    private final String prop_dis_page_prefix = "https://www.wikidata.org/wiki/Property_talk:";
    private String props_file;
    private List<ItemDiscussionPage> pages;

    public PropertyDiscussionPageGenerator(String props_file) {
        this.props_file = props_file;
        pages = new ArrayList<>();
    }

    public void run()  throws Exception {
        int count = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(props_file))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (count % 50 == 0)
                    System.out.println("DEBUG - processing page of property numbered" + count);
                URL url = new URL(prop_dis_page_prefix + line.trim());
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
        try (PrintWriter printWriter = new PrintWriter("data/raw/props_discussion.json")) {
            for (ItemDiscussionPage p : pages) {
                printWriter.println(gson.toJson(p));
            }
        }
    }


    public static void main(String[] args) throws Exception {
        PropertyDiscussionPageGenerator gen = new PropertyDiscussionPageGenerator("data/props_with_discussion.csv");
        gen.run();
    }
}
