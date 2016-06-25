package danon.Cymbergaj.View.Renderer;


import danon.Cymbergaj.Model.World.Control.Spaceship;
import danon.Cymbergaj.View.AnimatedSpriteSheet;

import java.awt.*;


public class SpaceshipRenderer implements Renderer {

    private final Spaceship spaceship;
    private final AnimatedSpriteSheet spriteSheet;

    public SpaceshipRenderer(Spaceship spaceship, ImagesRepository images) {
        this.spaceship = spaceship;
        this.spriteSheet = images.spaceship.getSpriteSheet();
    }

    @Override
    public void update(double elapsedTime) {
        double traveled = spaceship.getTraveledDistance();
        int frame = (int) Math.round(traveled / 4.0);
        spriteSheet.setFrame(frame);
    }

    @Override
    public void renderOn(Graphics2D canvas) {

    }
}
