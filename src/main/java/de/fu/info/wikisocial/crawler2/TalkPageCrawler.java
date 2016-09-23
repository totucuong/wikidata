package de.fu.info.wikisocial.crawler2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.fu.info.wikisocial.wikidata.DiscussionThread;
import de.fu.info.wikisocial.wikidata.model.TalkPage;
import de.fu.info.wikisocial.wikidata.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

/**
 * Created by totucuong on 8/12/16.
 * @TODO: add archive links too.
 */
public class TalkPageCrawler extends WebCrawlerImpl {
    private final ArrayList<User> users;

    private ArrayList<DiscussionThread> discussionThreads;

    private AtomicInteger counter = new AtomicInteger(0);

    public TalkPageCrawler(ArrayList<User> users) {
        this.users = users;
        discussionThreads = new ArrayList<>();
    }

    @Override
    public void start() {
        super.start();
        crawl();
    }

    public void print() {
        for (DiscussionThread t : discussionThreads) {
            System.out.println(t.get_owner());
            System.out.println(t.get_content());
            System.out.println("========================================================================================");
        }
    }

    @Override
    public void end() {
        super.end();
    }

    /**
     *  Collect discussion threads from all Wikidata users.
     */
    private void crawl() {
        for (User u : users) {
            String owner = u.getUser_name();
            if (counter.incrementAndGet() % 100 == 0) {
                LOG.log(Level.INFO, "processing the " + counter.get() + "th page - owner: " + owner);

            }
            TalkPage page = new TalkPage(u.getTalk_page(), owner);
            ArrayList<String> threads = page.get_threads();
            if (threads != null) {
                for (String t : page.get_threads()) {
                    discussionThreads.add(new DiscussionThread(t, owner));
                }
            }

        }
    }

    @Override
    public void saveToFile(String pathfile) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try (PrintWriter printWriter = new PrintWriter(pathfile)) {
            for (DiscussionThread t : discussionThreads) {
                printWriter.println(gson.toJson(t));
            }
        }
    }
}
