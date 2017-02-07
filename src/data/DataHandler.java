package data;

import controller.Controller;
import jdo.User;
import threads.ClientReadThread;
import threads.ClientWriteThread;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by LeoAsp on 2017-01-31.
 */
public class DataHandler {
    Controller controller;
    User user;
    ClientReadThread readThread;
    ClientWriteThread writeThread;


    public DataHandler(Socket socket, User user, Controller controller) throws IOException {
        this.controller = controller;
        this.user = user;
        readThread = new ClientReadThread(socket, user, controller);
        writeThread = new ClientWriteThread(socket, user);

        new Thread(readThread).start();
        new Thread(writeThread).start();
    }

    public void send(String message) {
        writeThread.send(message);
    }
}
