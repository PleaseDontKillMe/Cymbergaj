package Application.Model.World.Control;


import java.awt.event.KeyEvent;

public class ArrowsControlKeys extends ControlKeys {

    @Override
    boolean verifyUp(KeyEvent event) {
        return event.getKeyCode() == 38;
    }

    @Override
    boolean verifyDown(KeyEvent event) {
        return event.getKeyCode() == 40;
    }

    @Override
    boolean verifyLeft(KeyEvent event) {
        return event.getKeyCode() == 37;
    }

    @Override
    boolean verifyRight(KeyEvent event) {
        return event.getKeyCode() == 39;
    }
}
