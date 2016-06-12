package Application.Model.World.Character;

import Application.Geometry.Point;
import Application.View.ExplosionRenderer;
import Application.View.ImagesRepository;
import Application.View.Renderer;

public class Explosion {
    private final Point position;

    public Explosion(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new ExplosionRenderer(this, images);
    }
}