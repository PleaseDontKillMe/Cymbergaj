package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.View.Renderer.ExplosionRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;

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