package jdo;

import java.util.List;

/**
 * Created by LeoAsp on 2017-02-07.
 */
public class Room {
    public String roomName;
    public int playersConected;
    public int maxPlayers;
    public List<String> categories;

    public Room(String roomName, int playersConected, int maxPlayers) {
        this.roomName = roomName;
        this.playersConected = playersConected;
        this.maxPlayers = maxPlayers;
    }

    public Room(List<String> categories) {
        this.categories = categories;
    }


    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
