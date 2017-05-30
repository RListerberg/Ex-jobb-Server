package commands;

import com.google.gson.Gson;
import jdo.client.SimpleRoom;
import jdo.client.SimpleUser;
import jdo.server.Room;
import jdo.server.User;

import java.util.ArrayList;
import java.util.List;


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

    public String makeUpdateRoomPlayerList(List<User> users) {
        List<SimpleUser> simpleUsers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            simpleUsers.add(new SimpleUser(users.get(i)));
        }
        Command command = new Command(CommandType.UPDATEROOMPLAYERLIST, gson.toJson(simpleUsers));
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

    public String makeSendMessageCommand(String message) {
        Command command = new Command(CommandType.SENDMESSAGE, message);
        String stringCommand = gson.toJson(command);
        System.out.println("MADE: " + stringCommand);
        return stringCommand;
    }
}
