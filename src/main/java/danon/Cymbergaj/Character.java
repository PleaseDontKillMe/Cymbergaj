package danon.Cymbergaj;

import danon.Cymbergaj.Model.Updatable;
import danon.Cymbergaj.Model.World.Character.GameObject;
import danon.Cymbergaj.Model.World.Control.ControlKeys;
import danon.Cymbergaj.Model.World.Control.Keys;
import danon.Cymbergaj.View.Renderer.CharacterRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.PostureChangedListener;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Vector2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Character extends GameObject implements KeyListener, Updatable {
    private final static double VELOCITY = 5.0;

    private final ControlKeys controlKeys;
    private final Keys keys;

    private WeaponType weaponType = WeaponType.Shotgun;
    private Posture posture = Posture.Idle;
    private float orientation = 0;

    PostureChangedListener listener;

    public Character(ControlKeys controlKeys) {
        this(controlKeys, new Keys());
    }

    public Character(ControlKeys controlKeys, Keys keys) {
        this.keys = keys;
        this.controlKeys = controlKeys;
        this.controlKeys.useKeys(this.keys);

        BodyFixture fixture = new BodyFixture(new Circle(1.5));
        fixture.setFriction(0.0);
        this.addFixture(fixture);
        this.setMass(Mass.Type.NORMAL);
    }

    @Override
    public CharacterRenderer getRenderer(ImagesRepository images) {
        CharacterRenderer renderer = new CharacterRenderer(this, images);
        listener = renderer;
        return renderer;
    }

    @Override
    public boolean isAsleep() {
        return false;
    }

    public void update(double elapsedTime) {

        Posture previous = this.posture;

        int speed = 0;
        this.posture = Posture.Idle;
        if (keys.isUp()) {
            speed += VELOCITY;
            this.posture = Posture.Move;
        }
        if (keys.isDown()) {
            speed -= VELOCITY;
            this.posture = Posture.Move;
        }

        if (keys.isLeft()) {
            orientation -= 2 * elapsedTime;
        }
        if (keys.isRight()) {
            orientation += 2 * elapsedTime;
        }

        if (keys.isAction1()) {
            this.posture = Posture.Melee;
        }

        if (keys.isAction2()) {
            weaponType = weaponType.next();
        }

        if (this.posture != previous) {
            changePosture(this.posture);
        }

        Vector2 velocity = new Vector2();
        velocity.x = Math.cos(orientation) * speed;
        velocity.y = Math.sin(orientation) * speed;
        this.setLinearVelocity(velocity);
    }

    private void changePosture(Posture newPosture) {
        listener.postureChanged(newPosture);
        this.posture = newPosture;
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

    public float getOrientation() {
        return orientation;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Posture getPosture() {
        return posture;
    }
}
