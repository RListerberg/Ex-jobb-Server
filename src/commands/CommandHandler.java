package commands;

import controller.Controller;
import data.Parser;
import jdo.Room;
import jdo.User;

/**
 * Created by Meister on 2017-02-06.
 */
public class CommandHandler {
    User user;
    Controller controller;
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
                user.setNickname(command.data);
                System.out.println(user.nickname);
                break;
            case GETLOBBYACT:
                System.out.println("RECIVED: GETLOBBYACT");
                controller.getRooms().add(new Room("Test", 0, 4));
                user.dataHandler.send(commandMaker.makeDrawLobbyAct(controller.getRooms()));
                System.out.println("SENT: DRAWLOBBYACT");
                break;
            default:
                System.out.println("Command Type Could Not Be Resolved");
                break;
        }
    }
}
