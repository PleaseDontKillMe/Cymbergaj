package danon.Cymbergaj.Model.World.Control;


import danon.Cymbergaj.Geometry.Bounds;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.Model.World.Character.Spaceship;

import java.awt.event.KeyEvent;

public class SpaceshipControl implements Control {
    private final Spaceship spaceship;
    private final ControlKeys keys;
    private boolean goDown = false, goUp = false, goLeft = false, goRight = false;

    public static double VELOCITY = 2f;

    public SpaceshipControl(Spaceship spaceship, ControlKeys keys) {
        this.spaceship = spaceship;
        this.keys = keys;
    }

    @Override
    public void update() {
        Point position = spaceship.getPosition();
        if (goDown) {
            position.y += VELOCITY;
            spaceship.incTraveledDistance(VELOCITY);
        }
        if (goUp) {
            position.y -= VELOCITY;
            spaceship.incTraveledDistance(-VELOCITY);
        }
        if (goLeft) {
            position.x -= VELOCITY;
            spaceship.incTraveledDistance(-VELOCITY);
        }
        if (goRight) {
            position.x += VELOCITY;
            spaceship.incTraveledDistance(VELOCITY);
        }
    }

    @Override
    public void reactToBounds(Bounds bounds) {
        Point position = spaceship.getPosition();
        position.y = Math.max(position.y, bounds.getTop());
        position.y = Math.min(position.y, bounds.getBottom());
        position.x = Math.max(position.x, bounds.getLeft());
        position.x = Math.min(position.x, bounds.getRight());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keys.verifyUp(e)) {
            goUp = true;
        }
        if (keys.verifyDown(e)) {
            goDown = true;
        }
        if (keys.verifyLeft(e)) {
            goLeft = true;
        }
        if (keys.verifyRight(e)) {
            goRight = true;
        }
        if (e.getKeyChar() == 32) {
            Fireball.VELOCITY = .1f;
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
        if (keys.verifyLeft(e)) {
            goLeft = false;
        }
        if (keys.verifyRight(e)) {
            goRight = false;
        }
        if (e.getKeyChar() == 32) {
            Fireball.VELOCITY = 2.1f;
        }
    }
}
