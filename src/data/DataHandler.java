package data;

import jdo.User;
import threads.ClientReadThread;
import threads.ClientWriteThread;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by LeoAsp on 2017-01-31.
 */
public class DataHandler {
    ClientReadThread readThread;
    ClientWriteThread writeThread;
    User user;
    Parser parser;

    public DataHandler(Socket socket, User user) throws IOException {
        readThread = new ClientReadThread(socket, user);
        writeThread = new ClientWriteThread(socket, user);
        this.user = user;
        parser = new Parser(user);

        new Thread(readThread).start();
        new Thread(writeThread).start();
    }

    public void send(String message) {
        writeThread.send(message);
    }
}
