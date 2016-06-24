package danon.Cymbergaj.Debug.dyn;

import danon.Cymbergaj.Model.World.Control.ControlKeys;
import danon.Cymbergaj.Model.World.Control.Keys;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends GameObject implements KeyListener {

    public final static double VELOCITY = 6.0;

    private final ControlKeys controlKeys;
    private final Keys keys;

    public Player(ControlKeys controlKeys) {
        this.keys = new Keys();
        this.controlKeys = controlKeys;
        this.controlKeys.useKeys(this.keys);
    }

    @Override
    public void renderMe(Graphics2D canvas) {

    }

    public void updateMe(double elapsedTime) {
        Vector2 velocity = new Vector2();

        if (keys.isUp()) {
            velocity.add(0, VELOCITY);
        }
        if (keys.isDown()) {
            velocity.add(0, -VELOCITY);
        }
        if (keys.isLeft()) {
            velocity.add(-VELOCITY, 0);
        }
        if (keys.isRight()) {
            velocity.add(VELOCITY, 0);
        }

        this.setLinearVelocity(velocity);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        controlKeys.keyDown(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        controlKeys.keyUp(event);
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // ignored
    }
}
