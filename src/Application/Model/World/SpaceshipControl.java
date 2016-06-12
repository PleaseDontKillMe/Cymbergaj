package Application.Model.World;


import Application.Geometry.Point;
import Application.Model.GameEventListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SpaceshipControl implements GameEventListener, Control, KeyListener {

    private final Spaceship spaceship;
    private final ControlKeys keys;
    private boolean goDown = false, goUp = false;

    public SpaceshipControl(Spaceship spaceship, ControlKeys keys) {
        this.spaceship = spaceship;
        this.keys = keys;
    }

    @Override
    public void update() {
        Point position = spaceship.getPosition();
        if (goDown) position.y += 5;
        if (goUp) position.y -= 5;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keys.verifyUp(e)) {
            goUp = true;
        }
        if (keys.verifyDown(e)) {
            goDown = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keys.verifyUp(e)) {
            goUp = false;
        }
        if (keys.verifyDown(e)) {
            goDown = false;
        }
    }
}
