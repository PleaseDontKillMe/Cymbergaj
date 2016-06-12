package Application.View.Renderer;


import Application.Geometry.Point;
import Application.Geometry.Angle;
import Application.Geometry.Rotation;
import Application.Model.World.Character.Spaceship;
import Application.View.AnimatedSpriteSheet;

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
        spriteSheet.drawOn(canvas, spaceship.getPosition(), new Rotation(new Angle(), new Point(32, 32)));
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
