package de.fu.info.wikisocial.executables;

import com.google.gson.Gson;
import de.fu.info.wikisocial.wikidata.DiscussionThread;

import java.io.*;
import java.util.HashSet;

/**
 * This program generate from threads_full the list of users whose talks page has a table of content.
 * Created by totucuong on 8/19/16.
 */
public class TalkPagesWithTOC {
    private HashSet<String> owners;
    private String path_to_threads;
    private String path_to_users;

    public TalkPagesWithTOC(String path_to_threads, String path_to_users) {
        owners = new HashSet<>();
        this.path_to_threads = path_to_threads;
        this.path_to_users = path_to_users;
    }

    public void generate_owners_file() {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(path_to_threads));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(path_to_users))
            ) {
                // read thread data
                Gson gson = new Gson();
                reader.lines().forEach(thread -> {
                    DiscussionThread t = gson.fromJson(thread, DiscussionThread.class);
                    owners.add(t.get_owner());
                });

                // write users' names to file
                for (String u : owners) {
                    writer.write(u);
                    writer.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("Problems with reading file");
        }

    }


    public static void main(String[] args) {
        ThreadStatistics thread_statistics = new ThreadStatistics("data/threads_full");
        TalkPagesWithTOC pages = new TalkPagesWithTOC("data/threads_full", "data/owners_toc.txt");
        pages.generate_owners_file();
    }
}
