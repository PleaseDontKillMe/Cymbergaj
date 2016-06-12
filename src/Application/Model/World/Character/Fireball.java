package Application.Model.World.Character;

import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Model.World.Control;
import Application.Model.World.FireballControl;
import Application.View.Renderer.FireballRenderer;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;

public class Fireball extends Body {
    private final Angle direction;
    private int velocity = 1;

    public Fireball(Point position, Angle direction) {
        super(position);
        this.direction = direction;
    }

    public Angle getAngle() {
        return direction;
    }

    public int getVelocity() {
        return velocity;
    }

    public Control getControl() {
        return new FireballControl(this);
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new FireballRenderer(this, images);
    }
}
