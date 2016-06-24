package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.View.Renderer.BigExplosionRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderable;

public class BigExplosion extends Explosion {
    public BigExplosion(Point position) {
        super(position);
    }

    @Override
    public Renderable getRenderer(ImagesRepository images) {
        return new BigExplosionRenderer(this, images);
    }
}
