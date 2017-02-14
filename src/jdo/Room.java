package jdo;

/**
 * Created by LeoAsp on 2017-02-07.
 */
public class Room {
    public String roomName;
    public int playersConected;
    public int maxPlayers;

    public Room(String roomName, int playersConected, int maxPlayers) {
        this.roomName = roomName;
        this.playersConected = playersConected;
        this.maxPlayers = maxPlayers;
    }
}
