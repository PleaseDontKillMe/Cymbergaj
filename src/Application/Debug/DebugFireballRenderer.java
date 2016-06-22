package Application.Debug;

import Application.Geometry.Angle;
import Application.Model.World.Character.Fireball;
import Application.Geometry.Point;
import Application.View.Renderer.Renderer;

import java.awt.*;

public class DebugFireballRenderer implements Renderer {

    private Fireball fireball;

    public DebugFireballRenderer(Fireball debugFireball) {
        this.fireball = debugFireball;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        Point p = fireball.getPosition();
        int r = fireball.getRadius();
        canvas.drawOval(p.getX() - r, p.getY() -r, 2*r, 2*r);

        Angle direction = fireball.getDirection();
        Point ind = p.find(30, direction);

        canvas.drawLine(p.getX(), p.getY(), ind.getX(), ind.getY());
    }
}
