package jdo.server;

import jdo.client.SimpleRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeoAsp on 2017-02-07.
 */
public class Room {
    public int id;
    public List<User> users;
    public String name;
    public int connectedPlayers;
    public int maxPlayers;
    public List<String> categories;

    public Room(SimpleRoom sr, int id) {
        this.id=id;
        this.name = sr.getName();
        this.maxPlayers = sr.getMaxPlayers();
        this.connectedPlayers = sr.getConnectedPlayers();
        this.categories = sr.getCategories();
        this.users = new ArrayList<>();
    }


    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
