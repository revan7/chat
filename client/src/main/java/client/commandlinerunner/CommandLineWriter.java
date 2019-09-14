package client.commandlinerunner;

import client.listeners.OnEventListener;
import model.message.BasicMessage;
import model.message.Message;

import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * This class intercepts messages from the commandline before
 * sending them to the appropriate listener so they can be send to the server.
 */
public class CommandLineWriter implements Runnable {

    private Scanner in;
    private OnEventListener listener;

    public CommandLineWriter(OnEventListener listener) {
        this.listener = listener;
        in = new Scanner(System.in);
    }

    public void run() {
        try {
            while (true) {
                sendMessageFromCommandLine();
                sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageFromCommandLine() {
        final String toSend = in.nextLine();
        final Message message = new BasicMessage();
        message.setBody(toSend);
        listener.onSendMessage(message);
    }
}
