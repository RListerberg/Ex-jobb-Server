package threads;

import jdo.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by LeoAsp on 2017-01-31.
 */
public class ClientWriteThread implements Runnable {
    private boolean running = true;
    private PrintWriter out;
    User user;
    private String currentMessage = "";
    private String queuedMessage = "";

    public ClientWriteThread(Socket socket, User user) throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        this.user = user;
    }

    public void send(String message) {
        queuedMessage = message;
    }

    @Override
    public void run() {
        while (running) {
            while (!currentMessage.equals(queuedMessage)) {
                out.println(queuedMessage);
                currentMessage = queuedMessage;
                System.out.println("SENT: " + currentMessage);

            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}