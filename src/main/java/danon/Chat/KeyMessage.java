package danon.Chat;

import danon.Cymbergaj.Model.World.Control.Keys;

import java.io.Serializable;

public class KeyMessage implements Serializable {
    private boolean keyUp;
    private boolean keyDown;
    private boolean keyLeft;
    private boolean keyRight;

    public KeyMessage(boolean keyUp, boolean keyDown, boolean keyLeft, boolean keyRight) {
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    public static KeyMessage fromKeys(Keys keys) {
        return new KeyMessage(keys.isUp(), keys.isDown(), keys.isLeft(), keys.isRight());
    }

    public boolean isKeyUp() {
        return keyUp;
    }

    public boolean isKeyDown() {
        return keyDown;
    }

    public boolean isKeyLeft() {
        return keyLeft;
    }

    public boolean isKeyRight() {
        return keyRight;
    }
}
