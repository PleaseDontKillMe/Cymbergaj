package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.View.Renderer.BasicRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Rectangle;

public class Floor extends GameObject {

    public Floor() {
        BodyFixture floorFixture = new BodyFixture(new Rectangle(20.0, 0.2));
        floorFixture.setRestitution(0.0);
        this.addFixture(floorFixture);
        this.setMass(Mass.Type.INFINITE);
    }

    @Override
    public Renderer getRenderer(ImagesRepository images) {
        return new BasicRenderer(this);
    }
}
