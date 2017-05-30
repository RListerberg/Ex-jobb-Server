package connectivity;

import controller.Controller;
import jdo.server.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserHandler {
    private Controller controller;
    public List<User> users = new ArrayList<>();

    public UserHandler(Controller controller) {
        this.controller = controller;
    }

    public void addUser(User user) throws IOException {
        users.add(user);

    }
}
