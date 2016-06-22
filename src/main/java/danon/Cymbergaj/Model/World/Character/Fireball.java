package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.Geometry.Angle;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Model.World.Control.FireballControl;
import danon.Cymbergaj.View.Renderer.FireballRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;

public class Fireball extends Body {
    private final Angle direction;

    public static float VELOCITY = 0.5f;

    public Fireball(Point position, Angle direction) {
        super(position);
        this.direction = direction;
    }

    public Angle getDirection() {
        return direction;
    }

    public float getVelocity() {
        return VELOCITY;
    }

    public FireballControl getControl() {
        return new FireballControl(this);
    }

    @Override
    public int getRadius() {
        return 8;
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new FireballRenderer(this, images);
    }
}
