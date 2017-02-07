package connectivity;

import controller.Controller;

import java.io.IOException;

/**
 * Created by Meister on 2017-02-06.
 */
public class ConnectionHandler {
    Controller controller;
    public ConnectionListener cl;
    public Thread clThread;

    public ConnectionHandler(Controller controller) throws IOException {
        this.controller = controller;
        this.cl = new ConnectionListener(controller, 8008);
        clThread = new Thread(cl);
        clThread.start();
    }
}
