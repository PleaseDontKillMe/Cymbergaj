package Application.Model.World;

import Application.Model.World.Character.Body;
import Application.Model.World.Character.Fireball;

import java.util.List;

public class FireballBounce {
    private final Fireball body;
    private final List<Body> bodies;

    public FireballBounce(Fireball body, List<Body> bodies) {
        this.body = body;
        this.bodies = bodies;
    }

    public void bounce() {

    }
}
