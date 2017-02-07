package commands;

/**
 * Created by Meister on 2017-02-06.
 */
public class Command {
    public CommandType type;
    public String data;

    public Command(CommandType type, String data) {
        this.type = type;
        this.data = data;
    }

}
