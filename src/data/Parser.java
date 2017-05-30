package data;

import com.google.gson.Gson;
import commands.Command;


public class Parser {
    Gson gson = new Gson();

    public Command parse(String message) {
        Command command = gson.fromJson(message, Command.class);
        return command;
    }
}
