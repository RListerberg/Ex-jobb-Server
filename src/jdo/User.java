package jdo;

import data.DataHandler;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Meister on 2017-02-06.
 */
public class User {
    public String nickname;
    public Socket socket;
    public DataHandler dataHandler;

    public User(Socket socket) throws IOException {
        this.socket = socket;
        this.dataHandler = new DataHandler(socket, this);
    }

    public void setNickname(String nickname){
        this.nickname=nickname;
    }
}
