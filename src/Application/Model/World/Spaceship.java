package Application.Model.World;

import Application.Geometry.Point;
import Application.Geometry.Size;
import Application.View.ImagesRepository;
import Application.View.Renderer;
import Application.View.SpaceshipRenderer;


public class Spaceship extends Character {

    private final ControlKeys keys;

    public Spaceship(Point position, ControlKeys keys) {
        super(position, new Size(150, 100));
        this.keys = keys;
    }

    @Override
    public Control getControl() {
        return new SpaceshipControl(this, keys);
    }

    @Override
    public Renderer getRenderer(ImagesRepository images) {
        return new SpaceshipRenderer(this, images);
    }
}
