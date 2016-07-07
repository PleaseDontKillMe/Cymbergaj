package danon.Chat;

public class TextMessage implements Message {
    private String message;

    public TextMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
