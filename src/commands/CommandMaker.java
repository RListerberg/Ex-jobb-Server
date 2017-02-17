package commands;

import com.google.gson.Gson;
import jdo.client.SimpleRoom;
import jdo.server.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeoAsp on 2017-02-07.
 */
public class CommandMaker {
    Gson gson = new Gson();

    public CommandMaker() {
    }

    public String makeUpdateNickCommand(String nick) {
        Command command = new Command(CommandType.UPDATENICK, nick);
        String stringCommand = gson.toJson(command);
        System.out.println("MADE: " + stringCommand);
        return stringCommand;
    }

    public String makeUpdateRoomNameCommand(String roomName) {
        Command command = new Command(CommandType.UPDATEROOMNAME, roomName);
        String stringCommand = gson.toJson(command);
        System.out.println("MADE: " + stringCommand);
        return stringCommand;
    }

    public String makeUpdateLobbyList(List<Room> rooms) {
        List<SimpleRoom> simpleRooms = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            simpleRooms.add(new SimpleRoom(rooms.get(i)));
        }
        Command command = new Command(CommandType.UPDATELOBBYLIST, gson.toJson(simpleRooms));
        String stringCommand = gson.toJson(command);
        System.out.println("MADE: " + stringCommand);
        return stringCommand;
    }

    public String makeUpdateRoomCommand(Room room) {
        Command command = new Command(CommandType.UPDATEROOM, gson.toJson(new SimpleRoom(room)));
        String stringCommand = gson.toJson(command);
        System.out.println("MADE: " + stringCommand);
        return stringCommand;
    }
}
