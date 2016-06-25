package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.View.Renderer.BasicRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;

public class Stopper extends GameObject {

    public Stopper() {
        BodyFixture fixture = new BodyFixture(new Rectangle(0.6, 3.5));
        fixture.setRestitution(0.0);
        this.addFixture(fixture);
        this.setMass(MassType.INFINITE);
    }

    @Override
    public Renderer getRenderer(ImagesRepository images) {
        return new BasicRenderer(this);
    }
}
