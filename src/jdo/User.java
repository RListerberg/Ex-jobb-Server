package jdo;

import controller.Controller;
import data.DataHandler;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Meister on 2017-02-06.
 */
public class User {
    Controller controller;
    public String nickname;
    public Socket socket;
    public DataHandler dataHandler;
    public boolean inRoom = false;
    public Room room;


    public User(Socket socket, Controller controller) throws IOException {
        this.socket = socket;
        this.dataHandler = new DataHandler(socket, this, controller);
        this.controller = controller;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
