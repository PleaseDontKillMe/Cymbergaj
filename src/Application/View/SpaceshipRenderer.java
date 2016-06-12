package Application.View;


import Application.Model.World.Character.Spaceship;

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
        if (refreshesToNextFrame == 2) {
            refreshesToNextFrame = 0;
            spriteSheet.next();
        }
        refreshesToNextFrame++;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
