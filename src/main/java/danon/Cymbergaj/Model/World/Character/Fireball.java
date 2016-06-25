package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.View.Renderer.FireballRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;

public class Fireball extends GameObject  {

    public Fireball() {
        BodyFixture ballFixture = new BodyFixture(new Circle(0.4));
        ballFixture.setRestitution(1.0);
        addFixture(ballFixture);
        setMass(MassType.NORMAL);
        setLinearDamping(0.05);
        setUserData("ball");
    }

    public FireballRenderer getRenderer(ImagesRepository images) {
        return new FireballRenderer(this, images);
    }
}
