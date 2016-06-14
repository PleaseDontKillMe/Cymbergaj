package Application.Model.World.Control;

import java.awt.event.KeyEvent;

public abstract class ControlKeys {
    abstract boolean verifyUp(KeyEvent event);
    abstract boolean verifyDown(KeyEvent event);
}
