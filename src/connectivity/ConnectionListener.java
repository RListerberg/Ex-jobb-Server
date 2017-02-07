package connectivity;

import controller.Controller;
import jdo.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by LeoAsp on 2017-01-30.
 */
public class ConnectionListener implements Runnable {
    Controller controller;
    boolean running = true;
    ServerSocket serverSocket;


    public ConnectionListener(Controller controller, int port) throws IOException {
        this.controller = controller;
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        System.out.println("Server started on port: " + serverSocket.getLocalPort());
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Someone connected, Hello " + socket.getPort());
                User user = new User(socket, controller);
                controller.getUserHandler().addUser(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
