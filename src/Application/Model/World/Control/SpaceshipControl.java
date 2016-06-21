package Application.Model.World.Control;


import Application.Geometry.Bounds;
import Application.Geometry.Point;
import Application.Model.World.Character.Fireball;
import Application.Model.World.Character.Spaceship;

import java.awt.event.KeyEvent;

public class SpaceshipControl implements Control {

    private final Spaceship spaceship;
    private final ControlKeys keys;
    private boolean goDown = false, goUp = false;

    public static double VELOCITY = 2;

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
    }

    @Override
    public void reactToBounds(Bounds bounds) {
        Point position = spaceship.getPosition();
        position.y = Math.max(position.y, bounds.getTop());
        position.y = Math.min(position.y, bounds.getBottom());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keys.verifyUp(e)) {
            goUp = true;
        }
        if (keys.verifyDown(e)) {
            goDown = true;
        }
        if (e.getKeyChar() == 32) {
            Fireball.VELOCITY = 2.1f;
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
        if (e.getKeyChar() == 32) {
            Fireball.VELOCITY = .3f;
        }
    }
}
