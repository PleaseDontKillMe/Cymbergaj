package Application.Geometry;

import java.awt.*;

public class Bounds {
    private final Point position;
    private final Size size;

    public Bounds(Point leftTopCorner, Size size) {
        this.position = leftTopCorner;
        this.size = size;
    }

    public float getTop() {
        return (float)position.y;
    }

    public float getBottom() {
        return (float)position.y + size.getHeight();
    }

    public Point getCenter() {
        Point point = new Point(position);
        return point.add(size.getWidth()/2, size.getHeight()/2);
    }

    public Shape asAwtShape() {
        return new Rectangle(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }
}
