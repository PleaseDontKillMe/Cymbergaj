package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Model.World.Character.GameObject;
import danon.Cymbergaj.View.Graphics2DRenderer;
import org.dyn4j.collision.Fixture;

import java.awt.*;

public class BasicRenderer extends BodyRenderer {

    public BasicRenderer(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void renderBody(Graphics2D canvas) {
        for (int i = 0; i < gameObject.getFixtureCount(); i++) {
            Fixture fix = gameObject.getFixture(i);
            Graphics2DRenderer.render(canvas, fix.getShape());
        }
    }

}
