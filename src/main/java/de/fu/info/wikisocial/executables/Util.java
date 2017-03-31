package de.fu.info.wikisocial.executables;

import de.fu.info.wikisocial.wikidata.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by totucuong-standard on 1/20/17.
 */
public class Util {
    public static ArrayList<User> getUsers(String filepath) {
        // read in users with talk page that has table of content
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {
            String cur;
            while  ((cur = in.readLine()) != null) {
                User u = new User();
                u.setUser_name(cur.replace(" ", "_"));
                users.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public static ArrayList<String> getProperties(String filepath) {
        // read in users with talk page that has table of content
        ArrayList<String> properties = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {
            String cur;
            while  ((cur = in.readLine()) != null) {
                properties.add(cur);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
