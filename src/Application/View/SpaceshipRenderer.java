package Application.View;


import Application.Geometry.Point;
import Application.Model.World.Spaceship;

import java.awt.*;


public class SpaceshipRenderer implements Renderer {

    private final Spaceship spaceship;
    private ImagesRepository images;

    public SpaceshipRenderer(Spaceship spaceship, ImagesRepository images) {
        this.spaceship = spaceship;
        this.images = images;
    }

    @Override
    public void renderOn(Graphics2D canvas) {

        Point position = spaceship.getPosition();

        canvas.drawImage(images.plane, position.getX(), position.getY(), 206, 151, null);

    }
}
