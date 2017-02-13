package jdo;

import java.util.List;

/**
 * Created by LeoAsp on 2017-02-07.
 */
public class Room {
    public User host;
    public List<User> users;
    public List<String> categories;

    public Room(User host, List<User> users, List<String> categories) {
        this.host = host;
        this.users = users;
        this.categories = categories;
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
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
