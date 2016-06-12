package Application.Model.World.Character;

import Application.Geometry.Point;
import Application.View.BigExplosionRenderer;
import Application.View.ImagesRepository;
import Application.View.Renderer;

public class BigExplosion extends Explosion {
    public BigExplosion(Point position) {
        super(position);
    }

    @Override
    public Renderer getRenderer(ImagesRepository images) {
        return new BigExplosionRenderer(this, images);
    }
}
