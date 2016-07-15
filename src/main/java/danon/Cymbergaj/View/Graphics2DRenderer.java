package danon.Cymbergaj.View;

import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Shape;

import java.awt.*;
import java.awt.geom.*;

public class Graphics2DRenderer {

    public static void render(Graphics2D g, Shape shape) {
        if (shape instanceof Circle) {
            render(g, (Circle) shape);
        } else if (shape instanceof Polygon) {
            render(g, (Polygon) shape);
        } else {
            throw new RuntimeException("Nie ma");
        }
    }

    private static void render(Graphics2D g, Circle circle) {
        double radius = circle.getRadius();
        Vector2 center = circle.getCenter();

        double radius2 = 2.0 * radius;
        Ellipse2D.Double c = new Ellipse2D.Double(
                (center.x - radius) * Application.SCALE,
                (center.y - radius) * Application.SCALE,
                radius2 * Application.SCALE,
                radius2 * Application.SCALE);

        // fill the shape
        g.setColor(Color.CYAN);
        g.fill(c);
        // draw the outline
        g.setColor(getOutlineColor(Color.CYAN));
        g.draw(c);

        // draw a line so that rotation is visible
        Line2D.Double l = new Line2D.Double(
                center.x * Application.SCALE,
                center.y * Application.SCALE,
                (center.x + radius) * Application.SCALE,
                center.y * Application.SCALE);
        g.draw(l);
    }

    private static void render(Graphics2D g, Polygon polygon) {
        Vector2[] vertices = polygon.getVertices();
        int l = vertices.length;

        // create the awt polygon
        Path2D.Double p = new Path2D.Double();
        p.moveTo(vertices[0].x * Application.SCALE, vertices[0].y * Application.SCALE);
        for (int i = 1; i < l; i++) {
            p.lineTo(vertices[i].x * Application.SCALE, vertices[i].y * Application.SCALE);
        }
        p.closePath();

        // fill the shape
        g.setColor(Color.CYAN);
        g.fill(p);
        // draw the outline
        g.setColor(getOutlineColor(Color.CYAN));
        g.draw(p);
    }

    private static Color getOutlineColor(Color color) {
        Color darker = color.darker();
        return new Color(darker.getRed(), darker.getGreen(), darker.getBlue(), color.getAlpha());
    }
}