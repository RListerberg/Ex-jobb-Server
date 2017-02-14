package jdo;

import java.util.List;

/**
 * Created by LeoAsp on 2017-02-07.
 */
public class Room {
    public User host;
    public List<User> users;
    public List<String> categoryNames;

    public Room(User host, List<User> users, List<String> categoryNames) {
        this.host = host;
        this.users = users;
        this.categoryNames = categoryNames;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<String> getCategories() {
        return categoryNames;
    }

    public void setCategories(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }
}
