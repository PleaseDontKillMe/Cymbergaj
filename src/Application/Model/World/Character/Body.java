package Application.Model.World.Character;

import Application.Geometry.Point;
import Application.Model.World.Control.Control;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;

public abstract class Body {

    private final Point position;

    Body(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public abstract int getRadius();

    public abstract Control getControl();

    public abstract Renderer getRenderer(ImagesRepository images);
}
