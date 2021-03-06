package jdo.client;

import jdo.server.Room;
import jdo.server.User;

import java.util.ArrayList;
import java.util.List;


public class SimpleRoom {
    int id;
    List<SimpleUser> users = new ArrayList<>();
    String name;
    int connectedPlayers;
    int maxPlayers;
    List<String> categories;

    public SimpleRoom(Room room) {
        this.id = room.id;
        this.name = room.name;
        this.connectedPlayers = room.connectedPlayers;
        this.maxPlayers = room.maxPlayers;
        this.categories = room.categories;

        for (User user : room.users) {
            users.add(new SimpleUser(user));
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConnectedPlayers() {
        return connectedPlayers;
    }

    public void setConnectedPlayers(int connectedPlayers) {
        this.connectedPlayers = connectedPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
