package danon.Cymbergaj.Debug.dyn;


import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;

import java.awt.*;
import java.awt.geom.AffineTransform;

abstract public class GameObject extends Body {
    private Color color;

    public GameObject() {
        this.color = new Color(
                (float) Math.random() * 0.5f + 0.5f,
                (float) Math.random() * 0.5f + 0.5f,
                (float) Math.random() * 0.5f + 0.5f);
    }

    public void render(Graphics2D canvas) {
        AffineTransform originalTransform = canvas.getTransform();
        canvas.transform(getLocalTransform());

        for (BodyFixture fixture : this.fixtures) {
            Graphics2DRenderer.render(canvas, fixture.getShape(), DebugApplication.SCALE, color);
            renderMe(canvas);
        }

        canvas.setTransform(originalTransform);
    }

    private AffineTransform getLocalTransform() {
        AffineTransform localTransform = new AffineTransform();
        localTransform.translate(transform.getTranslationX() * DebugApplication.SCALE, transform.getTranslationY() * DebugApplication.SCALE);
        localTransform.rotate(transform.getRotation());
        return localTransform;
    }

    abstract public void renderMe(Graphics2D canvas);
}
