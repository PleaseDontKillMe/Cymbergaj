package Application.View;


import Application.Model.World.Explosion;

import java.awt.Graphics2D;


public class ExplosionRenderer implements Renderer {
    private final Explosion explosion;
    private AnimatedSpriteSheet spriteSheet;

    public ExplosionRenderer(Explosion explosion, ImagesRepository images) {
        this.explosion = explosion;
        this.spriteSheet = images.explosion;
    }

    int refreshesToNextFrame = 0;
    @Override
    public void renderOn(Graphics2D canvas) {
        spriteSheet.drawOn(canvas, explosion.getPosition());
        refreshesToNextFrame++;
        if (refreshesToNextFrame == 2) {
            refreshesToNextFrame = 0;
            spriteSheet = spriteSheet.next();
        }
    }
}