package danon.Chat;

import java.io.Serializable;

public class TextMessage implements Serializable {
    private String message;

    public TextMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
