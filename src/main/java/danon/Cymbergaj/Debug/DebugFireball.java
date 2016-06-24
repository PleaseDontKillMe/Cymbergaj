package danon.Cymbergaj.Debug;

import danon.Cymbergaj.Geometry.Angle;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.Model.World.Control.ArrowsControlKeys;
import danon.Cymbergaj.Model.World.Control.FireballControl;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderable;

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
    public Renderable getRenderer(ImagesRepository images) {
        return new DebugFireballRenderer(this);
    }
}
