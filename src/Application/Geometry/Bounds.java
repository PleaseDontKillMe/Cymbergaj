package Application.Geometry;

import java.awt.*;

public class Bounds {
    private final Point position;
    private final Size size;

    public Bounds(Point leftTopCorner, Size size) {
        this.position = leftTopCorner;
        this.size = size;
    }

    public Point getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }

    public Shape asAwtShape() {
        return new Rectangle(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }
}
