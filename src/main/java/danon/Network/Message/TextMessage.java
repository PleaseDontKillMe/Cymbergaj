package danon.Network.Message;

class TextMessage implements Message {
    private final String message;

    TextMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
