package controller;

import connectivity.ConnectionHandler;
import connectivity.UserHandler;

import java.io.IOException;

/**
 * Created by Meister on 2017-02-06.
 */
public class Controller {
    ConnectionHandler connectionHandler;
    UserHandler userHandler;

    public Controller() throws IOException {
        this.connectionHandler = new ConnectionHandler(this);
        this.userHandler = new UserHandler(this);
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    public void setConnectionHandler(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public UserHandler getUserHandler() {
        return userHandler;
    }

    public void setUserHandler(UserHandler userHandler) {
        this.userHandler = userHandler;
    }
}
