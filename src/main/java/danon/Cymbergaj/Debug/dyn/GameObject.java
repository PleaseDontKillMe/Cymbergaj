package danon.Cymbergaj.Debug.dyn;


import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GameObject extends Body {
    protected Color color;

    public GameObject() {
        this.color = new Color(
                (float) Math.random() * 0.5f + 0.5f,
                (float) Math.random() * 0.5f + 0.5f,
                (float) Math.random() * 0.5f + 0.5f);
    }

    public void render(Graphics2D g) {
        // save the original transform
        AffineTransform ot = g.getTransform();

        // transform the coordinate system from world coordinates to local coordinates
        AffineTransform lt = new AffineTransform();
        lt.translate(this.transform.getTranslationX() * ExampleGraphics2D.SCALE, this.transform.getTranslationY() * ExampleGraphics2D.SCALE);
        lt.rotate(this.transform.getRotation());

        // apply the transform
        g.transform(lt);

        // loop over all the body fixtures for this body
        for (BodyFixture fixture : this.fixtures) {
            // get the shape on the fixture
            Convex convex = fixture.getShape();
            Graphics2DRenderer.render(g, convex, ExampleGraphics2D.SCALE, color);
        }

        // set the original transform
        g.setTransform(ot);
    }
}
