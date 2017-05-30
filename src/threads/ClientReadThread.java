package threads;

import commands.CommandHandler;
import controller.Controller;
import jdo.server.User;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClientReadThread implements Runnable {
    Controller controller;
    Socket socket;
    Scanner in;
    User user;
    CommandHandler commandHandler;
    private boolean running = true;

    public ClientReadThread(Socket socket, User user, Controller controller) throws IOException {
        this.socket = socket;
        in = new Scanner(new InputStreamReader(socket.getInputStream()));
        this.controller = controller;
        this.user = user;
        commandHandler = new CommandHandler(user, controller);

    }

    @Override
    public void run() {
        String incomingMessage;
        while (running) {
            while (in.hasNext()) {
                incomingMessage = in.nextLine();
                System.out.println(socket.getPort() + ": " + incomingMessage);
                commandHandler.handle(incomingMessage);
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
