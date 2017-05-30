package commands;


public class Command {
    public CommandType type;
    public String data;

    public Command(CommandType type, String data) {
        this.type = type;
        this.data = data;
    }

}
