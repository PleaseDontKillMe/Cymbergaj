package Application.Model.World.Character;

import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Model.World.Control.FireballControl;
import Application.View.Renderer.FireballRenderer;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;

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
