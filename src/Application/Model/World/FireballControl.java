package Application.Model.World;

import Application.Geometry.Angle;
import Application.Geometry.Bounds;
import Application.Geometry.Point;
import Application.Model.World.Character.Fireball;

public class FireballControl implements Control {

    private final Fireball fireball;

    public FireballControl(Fireball fireball) {
        this.fireball = fireball;
    }

    @Override
    public void update() {
        Point position = fireball.getPosition();
        Point newPosition = position.find(fireball.getVelocity(), fireball.getAngle());
        position.setSize(newPosition);
    }

    @Override
    public void reactToBounds(Bounds bounds) {
        Point position = fireball.getPosition();
        Angle angle = fireball.getAngle();
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
        fireball.getAngle().flipOf(angle);
    }
}
