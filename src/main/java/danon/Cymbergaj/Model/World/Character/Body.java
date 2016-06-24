package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Model.World.Control.Control;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderable;

public abstract class Body {

    private final Point position;

    Body(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public abstract Control getControl();

    public abstract int getRadius();

    public abstract Renderable getRenderer(ImagesRepository images);
}
