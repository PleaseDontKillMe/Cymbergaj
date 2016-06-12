package Application.Model.World.Character;

import Application.Geometry.Point;
import Application.View.Renderer.FireballRenderer;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;

public class Fireball {
    private Point position;

    public Fireball(Point position) {
        this.position = position;
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new FireballRenderer(this, images);
    }

    public Point getPosition() {
        return position;
    }
}
