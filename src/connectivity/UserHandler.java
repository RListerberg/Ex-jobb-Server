package connectivity;

import controller.Controller;
import jdo.User;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meister on 2017-02-06.
 */
public class UserHandler {
    private Controller controller;
    public List<User> users = new ArrayList<>();

    public UserHandler(Controller controller) {
        this.controller = controller;
    }

    public void addUser(Socket socket) throws IOException {
        users.add(new User(socket));

    }
}
