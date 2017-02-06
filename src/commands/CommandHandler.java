package commands;

import jdo.User;

/**
 * Created by Meister on 2017-02-06.
 */
public class CommandHandler {
    User user;

    public CommandHandler(User user) {
        this.user = user;
    }

    public void handle(Command command) {
        switch (command.type) {
            case SETNICK:
                user.setNickname(command.data);
                System.out.println(user.nickname);
                break;
        }
    }
}
