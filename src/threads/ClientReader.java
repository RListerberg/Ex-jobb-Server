package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by LeoAsp on 2017-01-30.
 */
public class ClientReader implements Runnable {
    Socket socket;
    BufferedReader bufferedReader;

    public ClientReader(Socket socket) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            System.out.println(socket.getPort()+": "+bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
