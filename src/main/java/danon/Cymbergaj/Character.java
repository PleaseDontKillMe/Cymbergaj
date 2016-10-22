package danon.Cymbergaj;

import danon.Cymbergaj.Model.Updatable;
import danon.Cymbergaj.Model.World.Character.GameObject;
import danon.Cymbergaj.Model.World.Control.ControlKeys;
import danon.Cymbergaj.Model.World.Control.Keys;
import danon.Cymbergaj.View.Renderer.CharacterRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Vector2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Character extends GameObject implements KeyListener, Updatable {

    private final static double VELOCITY = 9.0;

    private final ControlKeys controlKeys;
    private final Keys keys;

    public Character(ControlKeys controlKeys) {
        this(controlKeys, new Keys());
    }

    public Character(ControlKeys controlKeys, Keys keys) {
        this.keys = keys;
        this.controlKeys = controlKeys;
        this.controlKeys.useKeys(this.keys);

        BodyFixture fixture = new BodyFixture(new Circle(0.7));
        fixture.setFriction(0.0);
        this.addFixture(fixture);
        this.setMass(Mass.Type.NORMAL);
    }

    @Override
    public CharacterRenderer getRenderer(ImagesRepository images) {
        return new CharacterRenderer(this, images);
    }

    @Override
    public boolean isAsleep() {
        return false;
    }

    public void update(double elapsedTime) {
        Vector2 velocity = new Vector2();

        if (keys.isUp()) {
            velocity.add(0, -VELOCITY);
        }
        if (keys.isDown()) {
            velocity.add(0, VELOCITY);
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
