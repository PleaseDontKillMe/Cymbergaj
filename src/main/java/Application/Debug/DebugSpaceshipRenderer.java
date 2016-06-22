package Application.Debug;

import Application.Geometry.Point;
import Application.Model.World.Character.Spaceship;
import Application.View.Renderer.Renderer;

import java.awt.*;


public class DebugSpaceshipRenderer implements Renderer {

    private final Spaceship spaceship;

    public DebugSpaceshipRenderer(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        Point p = spaceship.getPosition();
        int r = spaceship.getRadius();
        canvas.drawOval(p.getX() - r, p.getY() -r, 2*r, 2*r);
    }
}
