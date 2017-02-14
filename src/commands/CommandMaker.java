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

    public String makeSetNickCommand(String nick) {
        Command command = new Command(CommandType.SETNICK, nick);
        String stringCommand = gson.toJson(command);
        System.out.println("MADE: " + stringCommand);
        return stringCommand;
    }

    public String makeDrawLobbyAct(List<Room> rooms) {
        List<SimpleRoom> simpleRooms = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            simpleRooms.add(new SimpleRoom(rooms.get(i)));
        }
        Command command = new Command(CommandType.DRAWLOBBYACT, gson.toJson(simpleRooms));
        String stringCommand = gson.toJson(command);
        System.out.println("MADE: " + stringCommand);
        return stringCommand;
    }
}
