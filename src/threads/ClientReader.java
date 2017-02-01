package threads;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by LeoAsp on 2017-01-30.
 */
public class ClientReader implements Runnable {
    Socket socket;
    Scanner in;
    private boolean running = true;

    public ClientReader(Socket socket) throws IOException {
        this.socket = socket;
        in = new Scanner(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        String incommingMessage;
        while (running) {
            while (in.hasNext()) {
                incommingMessage = in.nextLine();
                System.out.println(socket.getPort() + ": " + incommingMessage);

            }
        }
    }
}
