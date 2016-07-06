package danon.Chat;

import java.io.Serializable;

class TextMessage implements Serializable {
    private String message;

    TextMessage(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }
}
