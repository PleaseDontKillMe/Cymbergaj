package Application.View;


import Application.Model.World.Spaceship;

import java.awt.*;


public class SpaceshipRenderer implements Renderer {

    private final Spaceship spaceship;
    private AnimatedSpriteSheet spriteSheet;
    private int refreshesToNextFrame = 0;

    public SpaceshipRenderer(Spaceship spaceship, ImagesRepository images) {
        this.spaceship = spaceship;
        this.spriteSheet = images.spaceship;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        spriteSheet.drawOn(canvas, spaceship.getPosition());
        refreshesToNextFrame++;
        if (refreshesToNextFrame == 6) {
            refreshesToNextFrame = 0;
            spriteSheet = spriteSheet.next();
        }
    }
}
