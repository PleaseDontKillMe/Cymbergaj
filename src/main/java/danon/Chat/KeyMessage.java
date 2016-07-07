package danon.Chat;

import danon.Cymbergaj.Model.World.Control.Keys;

public class KeyMessage implements Message {
    private char player;
    private boolean keyUp;
    private boolean keyDown;
    private boolean keyLeft;
    private boolean keyRight;

    private KeyMessage(char player, boolean keyUp, boolean keyDown, boolean keyLeft, boolean keyRight) {
        this.player = player;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    public static KeyMessage fromKeys(char player, Keys keys) {
        return new KeyMessage(player, keys.isUp(), keys.isDown(), keys.isLeft(), keys.isRight());
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

    public char getPlayer() {
        return player;
    }
}
