package jdo.server;

import controller.Controller;
import data.DataHandler;
import jdo.PlayerData;

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
    private boolean inRoom = false;
    public PlayerData playerData;


    public User(Socket socket, Controller controller) throws IOException {
        this.socket = socket;
        this.dataHandler = new DataHandler(socket, this, controller);
        this.controller = controller;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void makeUserPlayer() {
        playerData = new PlayerData();
    }

    public void makeUserNotPlayer() {
        playerData = null;
    }

    public boolean isInRoom() {
        return inRoom;
    }

    public void setInRoom(boolean inRoom) {
        this.inRoom = inRoom;
        if(inRoom){
            makeUserPlayer();
        } else if(!inRoom){
            makeUserNotPlayer();
        }else{
            System.out.println("User.class setInRoom ERROR");
        }
    }
}
