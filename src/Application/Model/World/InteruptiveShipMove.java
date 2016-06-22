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

    public void moveWithInterupt(Spaceship spaceship) {
        this.spaceship = spaceship;
        //
        allBodies.forEach(this::interruptBody);
    }

    private void interruptBody(Body body) {

    }
}
