package threads;

import data.Parser;
import jdo.User;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by LeoAsp on 2017-01-30.
 */
public class ClientReadThread implements Runnable {
    Socket socket;
    Scanner in;
    User user;
    Parser parser;
    private boolean running = true;

    public ClientReadThread(Socket socket, User user) throws IOException {
        this.socket = socket;
        in = new Scanner(new InputStreamReader(socket.getInputStream()));
        this.user = user;
        parser = new Parser(user);
    }

    @Override
    public void run() {
        String incomingMessage;
        while (running) {
            while (in.hasNext()) {
                incomingMessage = in.nextLine();
                System.out.println(socket.getPort() + ": " + incomingMessage);
                parser.parse(incomingMessage);
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
