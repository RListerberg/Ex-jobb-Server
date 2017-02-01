package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by LeoAsp on 2017-01-31.
 */
public class ServerWriteThread implements Runnable{
    private boolean running = true;
    private PrintWriter out;
    private String currentMessage = "";
    private String queuedMessage = "";

    public ServerWriteThread(Socket socket) throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
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
