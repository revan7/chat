package model.utils;

import org.apache.commons.lang3.SerializationUtils;

import java.io.*;

import model.message.Message;

public final class Utils {

    public static Message readMessage(InputStream inputStream) throws IOException {
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        final int messageSize = dataInputStream.readInt();
        final byte[] buffer = new byte[messageSize];
        dataInputStream.readFully(buffer);
        return SerializationUtils.deserialize(buffer);
    }

    public static void writeMessage(Message message, OutputStream outputStream) throws IOException {
        final DataOutputStream out = new DataOutputStream(outputStream);
        final byte[] serializedMessage = SerializationUtils.serialize(message);
        out.writeInt(serializedMessage.length);
        out.write(serializedMessage);
    }

}
