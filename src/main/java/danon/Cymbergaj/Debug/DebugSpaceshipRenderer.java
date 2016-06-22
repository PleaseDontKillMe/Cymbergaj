package danon.Cymbergaj.Debug;

import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.View.Renderer.Renderer;

import java.awt.*;


public class DebugSpaceshipRenderer implements Renderer {

    private final Spaceship spaceship;

    public DebugSpaceshipRenderer(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        danon.Cymbergaj.Geometry.Point p = spaceship.getPosition();
        int r = spaceship.getRadius();
        canvas.drawOval(p.getX() - r, p.getY() -r, 2*r, 2*r);
    }
}
