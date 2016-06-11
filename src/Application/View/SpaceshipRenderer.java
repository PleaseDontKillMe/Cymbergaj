package Application.View;


import Application.Model.World.Spaceship;

import java.awt.*;


public class SpaceshipRenderer implements Renderer {

    private final Spaceship spaceship;
    private AnimatedSpriteSheet spriteSheet;
    private int i = 0;

    public SpaceshipRenderer(Spaceship spaceship, ImagesRepository images) {
        this.spaceship = spaceship;
        spriteSheet = images.spaceship;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        spriteSheet.drawOn(canvas, spaceship.getPosition());
        spriteSheet = spriteSheet.next();
        i++;
        if (i == 6) {
            i = 0;
            spriteSheet = spriteSheet.next();
        }
    }
}
