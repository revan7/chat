package server;

import model.message.Message;
import model.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.connection.ClientRequest;
import server.connection.EventsListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Server implements Runnable, EventsListener {

    private ServerSocket serverSocket;
    private Map<String, ClientRequest> connections;
    private int port;
    private final Logger logger = LogManager.getLogger(Server.class);

    public Server(int port) {
        connections = Collections.synchronizedMap(new HashMap<>());
        this.port = port;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Server running on port {}", port);
            while (true) {
                handleRequests(serverSocket.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void handleRequests(Socket socket) {
        final String remoteAddress = socket.getRemoteSocketAddress().toString();
        if (!connections.containsKey(remoteAddress)) {
            connections.put(remoteAddress, new ClientRequest(socket, this));
        }
    }

    public synchronized void broadcastMessage(Message message) {
        for (ClientRequest request : connections.values()) {
            try {
                Utils.writeMessage(message, request.getSocket().getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClientDisconnected(String key) {
        logger.info("{} disconnected.", key);
        connections.remove(key);
    }
}
