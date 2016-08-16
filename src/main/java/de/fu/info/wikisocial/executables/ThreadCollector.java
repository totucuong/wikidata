package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.wikidata.User;
import de.fu.info.wikisocial.wikidata.UserFileReader;
import de.fu.info.wikisocial.crawler2.TalkPageCrawler;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by totucuong on 8/12/16.
 * A program to collect discuss threads from Wikidata's user talk pages
 */
public class ThreadCollector {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java de.fu.info.wikisocial.executables.ThreadCollector <path-to-user-file> <output-path>");
        } else {
            UserFileReader userFileReader = new UserFileReader(args[0]);
            ArrayList<User> users = userFileReader.getUsers();
            TalkPageCrawler crawler = new TalkPageCrawler(users);
            crawler.start();
            crawler.saveToFile(args[1]);
//            crawler.print();
//            crawler.saveToFile(args[1]);
        }
    }
}
