package Application.View.Renderer;


import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Geometry.Rotation;
import Application.Model.World.Character.Spaceship;
import Application.View.AnimatedSpriteSheet;

import java.awt.*;


public class SpaceshipRenderer implements Renderer {

    private final Spaceship spaceship;
    private final AnimatedSpriteSheet spriteSheet;

    public SpaceshipRenderer(Spaceship spaceship, ImagesRepository images) {
        this.spaceship = spaceship;
        this.spriteSheet = images.spaceship.getSpriteSheet();
    }

    @Override
    public void update() {
        double traveled = spaceship.getTraveledDistance();
        int frame = (int) Math.round(traveled / 4.0);
        spriteSheet.setFrame(frame);
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        spriteSheet.drawOn(canvas, spaceship.getPosition(), new Rotation(new Angle(), new Point(32, 32)));
        Point p = spaceship.getPosition();
        canvas.fillOval(p.getX() - 3, p.getY() - 3, 6, 6);
    }
}
