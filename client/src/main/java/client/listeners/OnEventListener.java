package client.listeners;

import model.message.Message;

public interface OnEventListener {

    void onMessageReceived(Message message);

    void onSendMessage(Message message);

}
