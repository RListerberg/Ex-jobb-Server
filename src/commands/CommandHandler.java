package commands;


import java.util.List;


import controller.Controller;
import data.Parser;
import jdo.Room;
import jdo.User;
import jpa.JpqlCommands;

/**
 * Created by Meister on 2017-02-06.
 */
public class CommandHandler {
    User user;
    Room room;
	JpqlCommands jpqlCommands = new JpqlCommands();

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

	        case GETCATEGORIES:
                System.out.println("RECIEVED: GETCATEGORIES");
                List categoryList = jpqlCommands.getCategoryNames();
                System.out.println("CATEGORIES: " + categoryList);
                break;

            case GETLOBBYACT:
                System.out.println("RECIVED: GETLOBBYACT");
                user.dataHandler.send(commandMaker.makeDrawLobbyAct(controller.getRooms()));
                System.out.println("SENT: DRAWLOBBYACT");
                break;
            default:
                System.out.println("Command Type Could Not Be Resolved");
                break;

            case CREATEROOM:
                System.out.println("RECIEVED: CREATEROOM");
                break;


        }


    }
}
