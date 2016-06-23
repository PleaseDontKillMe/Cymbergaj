package danon.Cymbergaj.Model.World.Control;

import danon.Cymbergaj.Geometry.Angle;
import danon.Cymbergaj.Geometry.Bounds;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Model.World.Character.Fireball;

import java.awt.event.KeyEvent;

public class FireballControl implements Control {

    private final Fireball fireball;

    public FireballControl(Fireball fireball) {
        this.fireball = fireball;
    }

    @Override
    public void update(double elapsedTime) {
        moveAlong(fireball.getVelocity());
    }

    public void moveAlong(double moveAmount) {
        Point position = fireball.getPosition();
        Point newPosition = position.find(moveAmount, fireball.getDirection());
        position.setSize(newPosition);
    }

    @Override
    public void reactToBounds(Bounds bounds) {
        Point position = fireball.getPosition();
        Angle angle = fireball.getDirection();
        bounceTop(bounds, position, angle);
        bounceBottom(bounds, position, angle);
    }

    private void bounceTop(Bounds bounds, Point position, Angle angle) {
        float diff = (float) position.y - bounds.getTop();
        if (diff < 0) {
            position.y = bounds.getTop() - diff;
            angle.flipHorizontal();
        }
    }

    private void bounceBottom(Bounds bounds, Point position, Angle angle) {
        float diff = (float) position.y - bounds.getBottom();
        if (diff > 0) {
            position.y = bounds.getBottom() - diff;
            angle.flipHorizontal();
        }
    }

    public void bounceAngle(Angle angle) {
        Angle newDirection = fireball.getDirection().flipOf(angle);
        fireball.getDirection().setValue(newDirection.getValue());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 8) {
            fireball.getDirection().flip();
        }
    }
}
