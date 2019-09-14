package server.connection;

import server.Server;

public class MainServer {

    public static void main(String[] args) {
        final int port = Integer.parseInt(System.getProperty("server.port"));
        final Server server = new Server(port);
    }

}
