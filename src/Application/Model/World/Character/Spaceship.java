package Application.Model.World.Character;

import Application.Geometry.Point;
import Application.Model.World.Control.Control;
import Application.Model.World.Control.ControlKeys;
import Application.Model.World.Control.SpaceshipControl;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;
import Application.View.Renderer.SpaceshipRenderer;


public class Spaceship extends Body {
    private final ControlKeys keys;

    public Spaceship(Point position, ControlKeys keys) {
        super(position);
        this.keys = keys;
    }

    @Override
    public int getRadius() {
        return 31;
    }

    public Control getControl() {
        return new SpaceshipControl(this, keys);
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new SpaceshipRenderer(this, images);
    }
}
