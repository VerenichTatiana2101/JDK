package src;

import src.client.ClientGui;
import src.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

        ServerWindow serverWindow = new ServerWindow();
        new ClientGui(serverWindow);
        new ClientGui(serverWindow);
    }
}
