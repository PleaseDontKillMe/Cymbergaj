package Application.Debug;

import Application.Geometry.Point;
import Application.Model.World.Character.Spaceship;
import Application.Model.World.Control.ControlKeys;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;

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
