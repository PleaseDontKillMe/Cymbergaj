package Application.Model.World.Character;

import Application.Geometry.Point;
import Application.Model.World.ControlKeys;
import Application.Model.World.KeyedControl;
import Application.Model.World.SpaceshipControl;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;
import Application.View.Renderer.SpaceshipRenderer;


public class Spaceship {
    private final Point position;
    public Point getPosition() {
        return position;
    }

    private final ControlKeys keys;

    public Spaceship(Point position, ControlKeys keys) {
        this.position = position;
        this.keys = keys;
    }

    public KeyedControl getControl() {
        return new SpaceshipControl(this, keys);
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new SpaceshipRenderer(this, images);
    }
}
