package Application.Model.World;


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
}
