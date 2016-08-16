package de.fu.info.wikisocial.executables;

/**
 * Created by totucuong on 8/16/16.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import com.google.gson.Gson;
import de.fu.info.wikisocial.wikidata.DiscussionThread;

/**
 * This class collect statistics about user talk pages.
 */
public class ThreadStatistics {

    private HashSet<String> owner;

    private HashSet<String> participants;

    private HashSet<String> items;

    private HashSet<String> properties;

    private int n_threads = 0;

    private String path_to_threads;

    ThreadStatistics(String path_to_threads) {
        this.path_to_threads = path_to_threads;
        owner = new HashSet<>();
        items = new HashSet<>();
        participants = new HashSet<>();
        properties = new HashSet<>();
    }

    private void read_data() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path_to_threads))) {
            Gson gson = new Gson();
            reader.lines().forEach(thread -> {
                DiscussionThread t = gson.fromJson(thread, DiscussionThread.class);
                owner.add(t.get_owner());
                participants.addAll(t.extract_users());
                items.addAll(t.extract_items());
                properties.addAll(t.extract_properties());
                n_threads++;
            });
        }

        participants.addAll(owner);
    }

    public void collect() throws IOException {
        read_data();
    }

    public int number_of_owners() {
        return owner.size();
    }

    public int number_of_participants() {
        return participants.size();
    }

    public int number_of_items() {
        return items.size();
    }

    public int number_of_properties() {
        return properties.size();
    }

    public int number_of_threads() {
        return n_threads;
    }

    public static void main(String[] args) {
        ThreadStatistics thread_statistics = new ThreadStatistics("data/threads_full");
        try {
            thread_statistics.collect();
            System.out.println("Number of owner: " + thread_statistics.number_of_owners());
            System.out.println("Number of participants: " + thread_statistics.number_of_participants());
            System.out.println("Number of items: " + thread_statistics.number_of_items());
            System.out.println("Number of properties: " + thread_statistics.number_of_properties());
            System.out.println("Number of threads:" + thread_statistics.n_threads);
        } catch (IOException iex) {
            iex.printStackTrace();
        }
    }
}
