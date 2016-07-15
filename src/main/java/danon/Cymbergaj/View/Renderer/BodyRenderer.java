package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Model.World.Character.GameObject;
import org.dyn4j.geometry.Transform;

import java.awt.*;
import java.awt.geom.AffineTransform;

abstract class BodyRenderer implements Renderer {

    final GameObject gameObject;

    BodyRenderer(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    protected abstract void renderBody(Graphics2D canvas);

    @Override
    public void renderOn(Graphics2D canvas) {
        AffineTransform originalTransform = canvas.getTransform();

        canvas.transform(getLocalTransform());
        renderBody(canvas);

        canvas.setTransform(originalTransform);
    }

    private AffineTransform getLocalTransform() {
        Transform transform = gameObject.getTransform();
        AffineTransform localTransform = new AffineTransform();
        localTransform.translate(transform.getTranslationX() * Application.SCALE, transform.getTranslationY() * Application.SCALE);
        return localTransform;
    }
}
