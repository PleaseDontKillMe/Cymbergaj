package Application.Model.World;

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

    }
}
