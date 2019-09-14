package client.guirunner;

import client.Runner;
import client.view.ChatFrame;
import model.connection.Address;
import model.message.Message;

/**
 * This is the GUI runner, handles creating the GUI frame, and acts as
 * See {@link Runner} for more details.
 */
public class GUIRunner extends Runner {

    private ChatFrame frame;

    public GUIRunner(Address address, String username) {
        super(address, username);
        frame = new ChatFrame(this);
    }

    @Override
    public void init() {
        super.init();
        new Thread(frame).start();
    }

    @Override
    public synchronized void onMessageReceived(Message message) {
        super.onMessageReceived(message);
        frame.appendNewMessage(message);
    }

}
