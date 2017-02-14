package commands;

import com.google.gson.Gson;
import jdo.server.Room;

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

    public String makeDrawLobbyAct(List<Room> roomList) {
        Command command = new Command(CommandType.DRAWLOBBYACT, gson.toJson(roomList));
        String stringCommand = gson.toJson(command);
        System.out.println("MADE: " + stringCommand);
        return stringCommand;
    }
}
