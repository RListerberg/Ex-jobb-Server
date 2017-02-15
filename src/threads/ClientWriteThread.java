package threads;

import jdo.server.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeoAsp on 2017-01-31.
 */
public class ClientWriteThread implements Runnable {
    Socket socket;
    User user;
    private boolean running = true;
    private PrintWriter out;
    private List<String> queuedMessages;

    public ClientWriteThread(Socket socket, User user) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        queuedMessages = new ArrayList<>();
        this.user = user;
    }

    public void send(String message) {
        queuedMessages.add(message);
    }

    @Override
    public void run() {
        while (running) {
            while (queuedMessages.size() > 0) {
                out.println(queuedMessages.get(0));
                System.out.println("SENT: " + queuedMessages.get(0));
                queuedMessages.remove(0);
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}