package Application.Debug;

import Application.Model.World.Character.Fireball;
import Application.Model.World.Control.ControlKeys;
import Application.Model.World.Control.FireballControl;

import java.awt.event.KeyEvent;

public class DebugFireballControl extends FireballControl {

    private final Fireball fireball;
    private final ControlKeys keys;
    private boolean goClockwise = false, goCounterClockwise = false;

    public DebugFireballControl(Fireball fireball, ControlKeys keys) {
        super(fireball);
        this.fireball = fireball;
        this.keys = keys;
    }

    @Override
    public void update() {
        if (goClockwise) {
            fireball.getDirection().inc(Math.PI/30);
        }
        if (goCounterClockwise) {
            fireball.getDirection().inc(-Math.PI/30);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keys.verifyRight(e)) {
            goClockwise = true;
        }
        if (keys.verifyLeft(e)) {
            goCounterClockwise = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keys.verifyRight(e)) {
            goClockwise = false;
        }
        if (keys.verifyLeft(e)) {
            goCounterClockwise = false;
        }
    }
}
