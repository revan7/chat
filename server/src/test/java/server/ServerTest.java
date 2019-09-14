package server;


import client.ClientConnection;
import model.connection.Address;
import model.message.BasicMessage;
import model.message.Message;
import model.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

class ServerTest {

    private static int port;
    private ClientConnection clientConnection;

    @BeforeAll
    public static void start() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(0);
        port = serverSocket.getLocalPort();
        serverSocket.close();
        Executors.newSingleThreadExecutor()
                .submit(() -> new Server(port));
        Thread.sleep(500);
    }

    @BeforeEach
    public void startClient() throws IOException {
        final Address address = new Address();
        address.setIp("127.0.0.1");
        address.setPort(port);
        clientConnection = new ClientConnection(address);
        clientConnection.startConnection();
    }

    @AfterEach
    public void tearDown() throws IOException {
        clientConnection.terminate();
    }

    @Test
    public void givenClient_broadcastMessageBack() throws IOException {
        final Message logInMessage = new BasicMessage();
        logInMessage.setSender("Dude");
        logInMessage.setBody("Has entered the chat !");
        Utils.writeMessage(logInMessage, clientConnection.getSocket().getOutputStream());
        final Message message = Utils.readMessage(clientConnection.getSocket().getInputStream());
        Assert.isTrue(message.getSender().equals(logInMessage.getSender()), "Check if sender of broadcast message is the same.");
        Assert.isTrue(message.getBody().equals(logInMessage.getBody()), "Check if body of broadcast message is the same.");
    }

}