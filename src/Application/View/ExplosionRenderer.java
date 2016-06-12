package Application.View;


import Application.Model.World.Character.Explosion;

import java.awt.Graphics2D;


public class ExplosionRenderer implements Renderer {
    private final Explosion explosion;
    private AnimatedSpriteSheet spriteSheet;

    public ExplosionRenderer(Explosion explosion, ImagesRepository images) {
        this.explosion = explosion;
        this.spriteSheet = images.explosion;
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