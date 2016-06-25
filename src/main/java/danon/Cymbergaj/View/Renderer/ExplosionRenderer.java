package danon.Cymbergaj.View.Renderer;


import danon.Cymbergaj.Model.World.Character.Explosion;
import danon.Cymbergaj.View.AnimatedSpriteSheet;

import java.awt.Graphics2D;


public class ExplosionRenderer implements Renderer {
    private final Explosion explosion;
    private final AnimatedSpriteSheet spriteSheet;

    public ExplosionRenderer(Explosion explosion, ImagesRepository images) {
        this.explosion = explosion;
        this.spriteSheet = images.explosion.getSpriteSheet();
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        spriteSheet.drawOn(canvas, explosion.getPosition());
        spriteSheet.next();
    }

    @Override
    public boolean isFinished() {
        return spriteSheet.isLast();
    }
}