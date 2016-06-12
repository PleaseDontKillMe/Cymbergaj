package Application.View;

import Application.Model.World.BigExplosion;

import java.awt.*;

public class BigExplosionRenderer implements Renderer {
    private final BigExplosion explosion;
    private AnimatedSpriteSheet spriteSheet;

    public BigExplosionRenderer(BigExplosion explosion, ImagesRepository images) {
        this.explosion = explosion;
        this.spriteSheet = images.bigExplosion;
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
