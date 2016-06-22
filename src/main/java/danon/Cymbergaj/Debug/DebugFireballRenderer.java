package danon.Cymbergaj.Debug;

import danon.Cymbergaj.Geometry.Angle;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.View.Renderer.Renderer;

import java.awt.*;

public class DebugFireballRenderer implements Renderer {

    private Fireball fireball;

    public DebugFireballRenderer(Fireball debugFireball) {
        this.fireball = debugFireball;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        danon.Cymbergaj.Geometry.Point p = fireball.getPosition();
        int r = fireball.getRadius();
        canvas.drawOval(p.getX() - r, p.getY() -r, 2*r, 2*r);

        Angle direction = fireball.getDirection();
        danon.Cymbergaj.Geometry.Point ind = p.find(30, direction);

        canvas.drawLine(p.getX(), p.getY(), ind.getX(), ind.getY());
    }
}
