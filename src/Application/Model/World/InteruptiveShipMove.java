package Application.Model.World;

import Application.Model.World.Character.Body;
import Application.Model.World.Character.Spaceship;

import java.util.List;

public class InteruptiveShipMove {
    private final List<Body> allBodies;
    private Spaceship spaceship;

    public InteruptiveShipMove(List<Body> bodies) {
        this.allBodies = bodies;
    }

    public void move(Spaceship spaceship) {
        this.spaceship = spaceship;
        allBodies.forEach(this::interruptBody);
    }

    private void interruptBody(Body body) {
        int commonRadius = spaceship.getRadius() + body.getRadius();
        double proximity = spaceship.getPosition().distanceTo(body.getPosition());
        if (commonRadius > proximity) {
            double intersection = proximity - commonRadius;

        }
    }
}
