package danon.Cymbergaj.Model.World.Control;


import java.awt.event.KeyEvent;

public class ArrowsControlKeys extends ControlKeys {

    @Override
    public boolean verifyUp(KeyEvent event) {
        return event.getKeyCode() == 38;
    }

    @Override
    public boolean verifyDown(KeyEvent event) {
        return event.getKeyCode() == 40;
    }

    @Override
    public boolean verifyLeft(KeyEvent event) {
        return event.getKeyCode() == 37;
    }

    @Override
    public boolean verifyRight(KeyEvent event) {
        return event.getKeyCode() == 39;
    }

    @Override
    protected boolean verifyAction1(KeyEvent event) {
        return false;
    }

    @Override
    protected boolean verifyAction2(KeyEvent event) {
        return false;
    }
}
