package danon.Cymbergaj.Model.World.Control;

import danon.Cymbergaj.Model.World.Character.Fireball;

import java.awt.event.KeyEvent;

public class FireballControl implements Control {

    private final Fireball fireball;

    public FireballControl(Fireball fireball) {
        this.fireball = fireball;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 8) {
            fireball.getDirection().flip();
        }
    }
}
