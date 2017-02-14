package jdo.client;

import jdo.server.Room;

import java.util.List;

/**
 * Created by LeoAsp on 2017-02-14.
 */
public class SimpleRoom {
    String name;
    int connectedPlayers;
    int maxPlayers;
    List<String> categories;

    public SimpleRoom(Room room) {
        this.name = room.name;
        this.connectedPlayers = room.connectedPlayers;
        this.maxPlayers = room.maxPlayers;
        this.categories = room.categories;
    }

}
