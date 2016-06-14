package Application.Model.World.Character;

import Application.Geometry.Point;
import Application.View.Renderer.ExplosionRenderer;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;

public class Explosion {
    private final Point position;

    Explosion(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new ExplosionRenderer(this, images);
    }
}