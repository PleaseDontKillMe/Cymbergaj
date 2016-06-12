package Application.View;


import Application.Model.World.Spaceship;

import java.awt.*;


public class SpaceshipRenderer implements Renderer {

    private final Spaceship spaceship;
    private AnimatedSpriteSheet spriteSheet;

    public SpaceshipRenderer(Spaceship spaceship, ImagesRepository images) {
        this.spaceship = spaceship;
        this.spriteSheet = images.spaceship;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        spriteSheet.drawOn(canvas, spaceship.getPosition());
        spriteSheet = spriteSheet.next();
    }

    @Override
    public boolean isFinished() {
        return spriteSheet.isLast();
    }
}
