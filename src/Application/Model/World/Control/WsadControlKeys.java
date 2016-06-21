package Application.Model.World.Control;

import java.awt.event.KeyEvent;

public class WsadControlKeys extends ControlKeys {

    @Override
    boolean verifyUp(KeyEvent event) {
        return event.getKeyChar() == 'w';
    }

    @Override
    boolean verifyDown(KeyEvent event) {
        return event.getKeyChar() == 's';
    }

    @Override
    boolean verifyLeft(KeyEvent event) {
        return event.getKeyChar() == 'a';
    }

    @Override
    boolean verifyRight(KeyEvent event) {
        return event.getKeyChar() == 'd';
    }
}
