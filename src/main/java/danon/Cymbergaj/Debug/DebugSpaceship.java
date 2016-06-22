package danon.Cymbergaj.Debug;

import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.Model.World.Control.ControlKeys;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;

public class DebugSpaceship extends Spaceship {

    private final ControlKeys controlKeys;

    public DebugSpaceship(Point point, ControlKeys controlKeys) {
        super(point, controlKeys);
        this.controlKeys = controlKeys;
    }

    @Override
    public int getRadius() {
        return 60;
    }

    @Override
    public Renderer getRenderer(ImagesRepository images) {
        return new DebugSpaceshipRenderer(this);
    }
}
