package client;

import client.listeners.OnEventListener;
import model.connection.Address;
import model.message.Message;
import model.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * This abstract class represents the main logic behind handling the connection client-side. It has added the basic implementation of
 * the communication between the client and server, which should be shared between the various implementations.({@link client.guirunner.GUIRunner} for example)
 */
public abstract class Runner implements OnEventListener, Runnable {

    private ClientConnection clientConnection;
    private final Logger logger = LogManager.getLogger(Runner.class);
    private String username;

    public Runner(Address address, String username) {
        this.username = username;
        clientConnection = new ClientConnection(address);
    }

    protected void init() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            logger.info("Runner started.");
            final Socket socket = clientConnection.startConnection();
            while (true) {
                logger.info("Waiting for message...");
                onMessageReceived(Utils.readMessage(socket.getInputStream()));
                sleep(10);
            }
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                clientConnection.terminate();
            } catch (IOException e) {
                logger.error("Error trying to terminate client socket.");
            }
        }
    }


    @Override
    public synchronized void onMessageReceived(Message message) {
        logger.info("Message received from " + message.getSender());
    }

    @Override
    public void onSendMessage(Message message) {
        try {
            message.setSender(username);
            Utils.writeMessage(message, clientConnection.getSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
