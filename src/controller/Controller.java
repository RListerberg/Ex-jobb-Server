package controller;

import connectivity.ConnectionHandler;
import connectivity.UserHandler;
import jdo.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meister on 2017-02-06.
 */
public class Controller {
    private ConnectionHandler connectionHandler;
    private UserHandler userHandler;
    private ArrayList<Room> rooms = new ArrayList<>();

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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}
