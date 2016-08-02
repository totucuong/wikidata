package de.fu.info.wikisocial;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


/**
 * Created by totucuong on 8/2/16.
 */
public class UserFileReader {

    private ArrayList<User> users;
    private String file_path;

    public UserFileReader(String file_path) {
        users = new ArrayList<User>();
        this.file_path = file_path;
        initialize();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    private void initialize() {
        try {
            Reader in = new FileReader(file_path);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader("user_id","user_name","user_real_name",
                    "user_password","user_newpassword","user_email","user_options","user_touched","user_token",
                    "user_email_authenticated","user_email_token","user_email_token_expires","user_registration",
                    "user_newpass_time","user_editcount","user_password_expires").parse(in);

            for (CSVRecord r : records) {
                User user = new User();
                user.setUser_id(r.get("user_id"));
                user.setUser_name(r.get("user_name"));
                user.setEdit_count(Integer.parseInt(r.get("user_editcount")));
                users.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
