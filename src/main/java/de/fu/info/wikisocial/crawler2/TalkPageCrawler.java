package de.fu.info.wikisocial.crawler2;

import de.fu.info.wikisocial.wikidata.DiscussionThread;
import de.fu.info.wikisocial.wikidata.TalkPage;
import de.fu.info.wikisocial.wikidata.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by totucuong on 8/12/16.
 */
public class TalkPageCrawler extends WebCrawlerImpl {
    private final ArrayList<User> users;

    private ArrayList<DiscussionThread> discussionThreads;

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
            TalkPage page = new TalkPage(u.getTalk_page(), owner);
            for (String t : page.get_threads()) {
                discussionThreads.add(new DiscussionThread(t, owner));
            }
        }
    }

    @Override
    public void saveToFile(String pathfile) throws IOException {

    }
}
