package client.view;

import client.listeners.OnEventListener;
import model.message.BasicMessage;
import model.message.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class ChatFrame extends JFrame implements Runnable, ActionListener {

    private MessagePanel messagePanel;
    private JTextArea chatMessages;
    private OnEventListener listener;
    private final Logger logger = LogManager.getLogger(ChatFrame.class);

    public ChatFrame(OnEventListener listener) {
        this.listener = listener;
        messagePanel = new MessagePanel();
        chatMessages = new JTextArea();
        chatMessages.setEditable(false);
        init();
    }

    void init() {
        this.setSize(400, 400);
        this.setMinimumSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(BorderLayout.SOUTH, messagePanel);
        this.getContentPane().add(BorderLayout.CENTER, chatMessages);
        this.setVisible(true);
        this.messagePanel.getButton().addActionListener(this);
        this.messagePanel.getMessageField().addActionListener(this);
    }

    public String getMessageText() {
        final JTextField messageField = messagePanel.getMessageField();
        final String text = messageField.getText();
        messageField.setText("");
        return text;
    }
    
    public void appendNewMessage(Message message) {
        final StringBuilder builder = new StringBuilder();
        builder.append(message.getSender()).append(":").append(message.getBody()).append("\n");
        chatMessages.append(builder.toString());
    }

    @Override
    public void run() {
        try {
            logger.info("Frame thread started !");
            while (true) {
                sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String messageText = getMessageText();
        Message message = new BasicMessage();
        message.setBody(messageText);
        listener.onSendMessage(message);
    }
}
