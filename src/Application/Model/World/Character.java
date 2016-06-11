package Application.Model.World;

import Application.Geometry.Point;
import Application.Geometry.Size;
import Application.View.ImagesRepository;
import Application.View.Renderer;

public abstract class Character {

    private final Point position;
    private final Size size;

    public Character(Point position, Size size) {
        this.position = position;
        this.size = size;
    }

    public Point getPosition() {
        return position;
    }

    public abstract Control getControl();

    public abstract Renderer getRenderer(ImagesRepository images);
}
