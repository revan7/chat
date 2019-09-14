package client.view;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {

    private JLabel label;
    private JTextField messageField;
    private JButton sendButton;

    public MessagePanel() {
        label = new JLabel("Enter text");
        this.messageField = new JTextField();
        sendButton = new JButton("Send");
        init();
    }

    private void init() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx= 0.1;
        this.add(label, constraints);
        GridBagConstraints messageFieldConstraints = new GridBagConstraints();
        messageFieldConstraints.gridx = 1;
        messageFieldConstraints.gridy = 0;
        messageFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        messageFieldConstraints.weightx= 1;
        messageFieldConstraints.gridwidth = 2;
        this.add(messageField, messageFieldConstraints);
        GridBagConstraints sendButtonConstraints = new GridBagConstraints();
        sendButtonConstraints.gridx = 3;
        sendButtonConstraints.gridy = 0;
        sendButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        sendButtonConstraints.weightx= 0.1;
        this.add(sendButton, sendButtonConstraints);
    }

    public JButton getButton() {
        return sendButton;
    }

    public JTextField getMessageField() {
        return messageField;
    }

}
