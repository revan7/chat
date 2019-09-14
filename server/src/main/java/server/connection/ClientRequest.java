package server.connection;

import model.message.Message;
import model.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;


public class ClientRequest implements Runnable {

    private EventsListener listener;
    private Socket socket;
    private final Logger logger = LogManager.getLogger(ClientRequest.class);

    public ClientRequest(Socket socket, EventsListener messageListener) {
        this.socket = socket;
        this.listener = messageListener;
        new Thread(this).start();
    }

    public void run() {
        logger.info("Connection established with : {}", socket.getRemoteSocketAddress().toString());
        try {
            while (true) {
                logger.debug("Message from {} received !", socket.getRemoteSocketAddress().toString());
                final Message message = Utils.readMessage(socket.getInputStream());
                listener.broadcastMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            listener.onClientDisconnected(socket.getRemoteSocketAddress().toString());
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

}
