package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.View.Renderer.ExplosionRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderable;

public class Explosion {
    private final Point position;

    Explosion(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public Renderable getRenderer(ImagesRepository images) {
        return new ExplosionRenderer(this, images);
    }
}