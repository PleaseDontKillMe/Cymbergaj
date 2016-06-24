package danon.Cymbergaj.Debug.dyn;


import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GameObject extends Body {
    private Color color;

    public GameObject() {
        this.color = new Color(
                (float) Math.random() * 0.5f + 0.5f,
                (float) Math.random() * 0.5f + 0.5f,
                (float) Math.random() * 0.5f + 0.5f);
    }

    public void render(Graphics2D canvas) {
        AffineTransform originalTransform = canvas.getTransform();

        // transform the coordinate system from world coordinates to local coordinates
        AffineTransform localTransform = new AffineTransform();
        localTransform.translate(this.transform.getTranslationX() * ExampleGraphics2D.SCALE, this.transform.getTranslationY() * ExampleGraphics2D.SCALE);
        localTransform.rotate(this.transform.getRotation());

        canvas.transform(localTransform);

        for (BodyFixture fixture : this.fixtures) {
            Convex convex = fixture.getShape();
            Graphics2DRenderer.render(canvas, convex, ExampleGraphics2D.SCALE, color);
        }

        canvas.setTransform(originalTransform);
    }
}
