package server.connection;

import model.message.Message;

public interface EventsListener {

    void broadcastMessage(Message message);

    void onClientDisconnected(String key);

}
