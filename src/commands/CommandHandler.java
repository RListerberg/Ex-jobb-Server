package commands;


import com.google.gson.Gson;
import controller.Controller;
import data.Parser;
import jdo.client.SimpleRoom;
import jdo.server.Room;
import jdo.server.User;
import jpa.JpqlCommands;

import java.util.List;

/**
 * Created by Meister on 2017-02-06.
 */
public class CommandHandler {
    Controller controller;
    JpqlCommands jpqlCommands = new JpqlCommands();
    Gson gson = new Gson();

    User user;
    Parser parser;
    CommandMaker commandMaker;


    public CommandHandler(User user, Controller controller) {
        this.controller = controller;
        this.user = user;
        parser = new Parser();
        commandMaker = new CommandMaker();
    }

    public void handle(String message) {
        Command command = parser.parse(message);
        switch (command.type) {
            case SETNICK:
                System.out.println("RECIVED: SETNICK");
                setNickname(command.data);
                updateNickname(command.data);
                break;
            case GETCATEGORIES:
                System.out.println("RECIEVED: GETCATEGORIES");
                List categoryList = jpqlCommands.getCategoryNames();
                System.out.println("CATEGORIES: " + categoryList);
                break;
            case GETLOBBYLIST:
                System.out.println("RECIVED: GETLOBBYLIST");
                updateLobbyList();
                break;
            case CREATEROOM:
                System.out.println("RECIEVED: CREATEROOM");
                SimpleRoom newRoom = gson.fromJson(command.data, SimpleRoom.class);
                controller.getRooms().add(new Room(newRoom));
                updateLobbyList();
                break;
            default:
                System.out.println("Command Type Could Not Be Resolved");
                break;

        }
    }

    public void updateLobbyList() {
        user.dataHandler.send(commandMaker.makeUpdateLobbyList(controller.getRooms()));
        System.out.println("SENT: UPDATELOBBYLIST");
    }

    public void setNickname(String nick) {
        user.setNickname(nick);
        System.out.println(user.socket.getPort() + " = " + user.nickname);
    }

    public void updateNickname(String nick){
        user.dataHandler.send(commandMaker.makeUpdateNickCommand(nick));
        System.out.println("SENT: UPDATENICKNAME");
    }

}
