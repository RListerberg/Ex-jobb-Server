package data;

import com.google.gson.Gson;
import commands.Command;
import commands.CommandHandler;
import jdo.User;

/**
 * Created by Meister on 2017-02-06.
 */
public class Parser {
    Gson gson = new Gson();
    User user;
    CommandHandler commandHandler;

    public Parser(User user) {
        this.user = user;
        commandHandler = new CommandHandler(user);
    }

    public void parse(String message) {
        Command command = gson.fromJson(message, Command.class);
        commandHandler.handle(command);
    }
}
