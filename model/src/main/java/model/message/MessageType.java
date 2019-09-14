package model.message;

public enum MessageType {

    CHAT("CHAT"),
    SERVICE("SERVICE");

    private final String name;

    MessageType(String name) {
        this.name = name;
    }

    private String getName() {
        return this.name;
    }
}
