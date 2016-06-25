package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.View.Renderer.BasicRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;

public class Wall extends GameObject {

    public Wall(String userData) {
        BodyFixture wallFixture = new BodyFixture(new Rectangle(0.2, 12.0));
        wallFixture.setRestitution(0.0);
        this.addFixture(wallFixture);
        this.setMass(MassType.INFINITE);
        this.setUserData(userData);
    }

    @Override
    public Renderer getRenderer(ImagesRepository images) {
        return new BasicRenderer(this);
    }
}
