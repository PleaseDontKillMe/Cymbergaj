package Application.Debug;

import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Model.World.Character.Fireball;
import Application.Model.World.Control.ArrowsControlKeys;
import Application.Model.World.Control.FireballControl;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;

public class DebugFireball extends Fireball {

    public DebugFireball(Point center, Angle angle) {
        super(center, angle);
    }

    @Override
    public int getRadius() {
        return 40;
    }

    @Override
    public FireballControl getControl() {
        return new DebugFireballControl(this, new ArrowsControlKeys());
    }

    @Override
    public Renderer getRenderer(ImagesRepository images) {
        return new DebugFireballRenderer(this);
    }
}
