package Application.Model.World;

import Application.Model.World.Character.Fireball;

public class FireballControl implements Control {

    private final Fireball fireball;

    public FireballControl(Fireball fireball) {
        this.fireball = fireball;
    }

    @Override
    public void update() {
        // TODO implement fireball movement
    }
}
