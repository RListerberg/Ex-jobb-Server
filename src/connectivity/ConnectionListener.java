package connectivity;

import threads.ClientReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by LeoAsp on 2017-01-30.
 */
public class ConnectionListener implements Runnable {
    boolean running = true;
    ServerSocket serverSocket;


    public ConnectionListener(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                new Thread(new ClientReader(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
