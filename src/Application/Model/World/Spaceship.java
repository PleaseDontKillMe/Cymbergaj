package Application.Model.World;

import Application.Geometry.Point;
import Application.Geometry.Size;
import Application.View.Images;
import Application.View.Renderer;
import Application.View.SpaceshipRenderer;


public class Spaceship extends Character {

    public Spaceship(Point position) {
        super(position, new Size(150, 100));
    }

    @Override
    public int getMaxHealthPoints() {
        return 20;
    }

    @Override
    public Control getControl() {
        return new SpaceshipControl(this);
    }

    @Override
    public Renderer getRenderer(Images images) {
        return new SpaceshipRenderer(this, images);
    }
}
