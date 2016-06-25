package danon.Cymbergaj.Model.World.Control;

import java.awt.event.KeyEvent;

public abstract class ControlKeys {
    private Keys keys;

    public void useKeys(Keys keys) {
        this.keys = keys;
    }

    public void keyDown(KeyEvent event) {
        if (verifyLeft(event)) {
            keys.setLeft(true);
        }
        if (verifyRight(event)) {
            keys.setRight(true);
        }
        if (verifyUp(event)) {
            keys.setUp(true);
        }
        if (verifyDown(event)) {
            keys.setDown(true);
        }
    }

    public void keyUp(KeyEvent event) {
        if (verifyLeft(event)) {
            keys.setLeft(false);
        }
        if (verifyRight(event)) {
            keys.setRight(false);
        }
        if (verifyUp(event)) {
            keys.setUp(false);
        }
        if (verifyDown(event)) {
            keys.setDown(false);
        }
    }

    abstract boolean verifyUp(KeyEvent event);

    abstract boolean verifyDown(KeyEvent event);

    abstract boolean verifyLeft(KeyEvent event);

    abstract boolean verifyRight(KeyEvent event);
}
