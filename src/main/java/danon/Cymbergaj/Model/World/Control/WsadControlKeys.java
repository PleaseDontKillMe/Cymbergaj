package danon.Cymbergaj.Model.World.Control;

import java.awt.event.KeyEvent;

public class WsadControlKeys extends ControlKeys {

    @Override
    public boolean verifyUp(KeyEvent event) {
        return event.getKeyChar() == 'w';
    }

    @Override
    public boolean verifyDown(KeyEvent event) {
        return event.getKeyChar() == 's';
    }

    @Override
    public boolean verifyLeft(KeyEvent event) {
        return event.getKeyChar() == 'a';
    }

    @Override
    public boolean verifyRight(KeyEvent event) {
        return event.getKeyChar() == 'd';
    }
}
