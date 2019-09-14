package client.commandlinerunner;

import client.Runner;
import model.connection.Address;
import model.message.Message;

/**
 * This class is the command line runner, which handles IO for the messages, delegating them to the appropriate
 * components.
 * The command line is very primitive, the logs and messages are output to the same
 * stream, so I really recommend just using the GUI.
 * See {@link Runner} for more details.
 */
public class CommandLineRunner extends Runner {

    private CommandLineWriter writer;

    public CommandLineRunner(Address address, String username) {
        super(address, username);
        writer = new CommandLineWriter(this);
    }

    @Override
    public void init() {
        super.init();
        new Thread(writer).start();
    }

    public synchronized void onMessageReceived(Message message) {
        super.onMessageReceived(message);
        StringBuilder builder = new StringBuilder();
        builder.append(message.getSender()).append(":").append(message.getBody());
        System.out.println(builder.toString());
    }

}
