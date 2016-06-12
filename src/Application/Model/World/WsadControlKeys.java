package Application.Model.World;

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
}
