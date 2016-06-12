package Application.Model.World.Character;

import Application.Geometry.Point;
import Application.View.Renderer.BigExplosionRenderer;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;

public class BigExplosion extends Explosion {
    public BigExplosion(Point position) {
        super(position);
    }

    @Override
    public Renderer getRenderer(ImagesRepository images) {
        return new BigExplosionRenderer(this, images);
    }
}
