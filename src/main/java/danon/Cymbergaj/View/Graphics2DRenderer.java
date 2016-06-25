package danon.Cymbergaj.View;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Shape;

import java.awt.*;
import java.awt.geom.*;

public class Graphics2DRenderer {

    public static void render(Graphics2D g, Shape shape, double scale, Color color, Body body) {
        if (body.isAsleep() || !body.isActive()) {
            color = Color.red;
        }
        if (shape instanceof Circle) {
            render(g, (Circle) shape, scale, color);
        } else if (shape instanceof Polygon) {
            render(g, (Polygon) shape, scale, color);
        } else {
            throw new RuntimeException("Nie ma");
        }
    }

    public static void render(Graphics2D g, Circle circle, double scale, Color color) {
        double radius = circle.getRadius();
        Vector2 center = circle.getCenter();

        double radius2 = 2.0 * radius;
        Ellipse2D.Double c = new Ellipse2D.Double(
                (center.x - radius) * scale,
                (center.y - radius) * scale,
                radius2 * scale,
                radius2 * scale);

        // fill the shape
        g.setColor(color);
        g.fill(c);
        // draw the outline
        g.setColor(getOutlineColor(color));
        g.draw(c);

        // draw a line so that rotation is visible
        Line2D.Double l = new Line2D.Double(
                center.x * scale,
                center.y * scale,
                (center.x + radius) * scale,
                center.y * scale);
        g.draw(l);
    }

    public static void render(Graphics2D g, Polygon polygon, double scale, Color color) {
        Vector2[] vertices = polygon.getVertices();
        int l = vertices.length;

        // create the awt polygon
        Path2D.Double p = new Path2D.Double();
        p.moveTo(vertices[0].x * scale, vertices[0].y * scale);
        for (int i = 1; i < l; i++) {
            p.lineTo(vertices[i].x * scale, vertices[i].y * scale);
        }
        p.closePath();

        // fill the shape
        g.setColor(color);
        g.fill(p);
        // draw the outline
        g.setColor(getOutlineColor(color));
        g.draw(p);
    }

    private static Color getOutlineColor(Color color) {
        Color darker = color.darker();
        return new Color(darker.getRed(), darker.getGreen(), darker.getBlue(), color.getAlpha());
    }
}