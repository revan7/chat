package client;

import model.connection.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

/**
 * This class represents the client connection between a client and a server.
 * It only handles client connection logic, the rest, writing to the output stream and receiving messages are
 * delegated to the appropriate entities.
 */
public class ClientConnection {

    private Socket socketConnection;
    private Address serverAddress;
    private final Logger logger = LogManager.getLogger(ClientConnection.class);

    public ClientConnection(Address serverAddress) {
        this.serverAddress = serverAddress;
    }

    public synchronized Socket startConnection() throws IOException {
        socketConnection = new Socket(serverAddress.getIp(), serverAddress.getPort());
        logger.info("Connection established!");
        return socketConnection;
    }

    public synchronized void terminate() throws IOException {
        socketConnection.close();
    }

    public synchronized Socket getSocket() {
        return socketConnection;
    }

}
