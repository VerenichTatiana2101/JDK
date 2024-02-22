package src;

import src.client.ClientGui;
import src.server.ServerWindow;

public class MainClientServer {
    public static void main(String[] args) {

        ServerWindow serverWindow = new ServerWindow();
        new ClientGui(serverWindow);
        new ClientGui(serverWindow);
    }
}
