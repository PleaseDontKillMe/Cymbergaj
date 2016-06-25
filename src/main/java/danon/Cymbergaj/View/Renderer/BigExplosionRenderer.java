package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Model.World.Character.BigExplosion;
import danon.Cymbergaj.View.AnimatedSpriteSheet;

import java.awt.*;

public class BigExplosionRenderer implements Renderer {
    private final BigExplosion explosion;
    private final AnimatedSpriteSheet spriteSheet;

    public BigExplosionRenderer(BigExplosion explosion, ImagesRepository images) {
        this.explosion = explosion;
        this.spriteSheet = images.bigExplosion.getSpriteSheet();
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
