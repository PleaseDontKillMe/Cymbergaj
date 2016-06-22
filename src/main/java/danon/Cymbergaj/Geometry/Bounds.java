package danon.Cymbergaj.Geometry;

import java.awt.*;

public class Bounds {
    private final danon.Cymbergaj.Geometry.Point position;
    private final Size size;

    public Bounds(danon.Cymbergaj.Geometry.Point leftTopCorner, Size size) {
        this.position = leftTopCorner;
        this.size = size;
    }

    public float getTop() {
        return (float) position.y;
    }

    public float getBottom() {
        return (float) position.y + size.getHeight();
    }

    public float getLeft() {
        return (float) position.x;
    }

    public float getRight() {
        return (float) position.x + size.getWidth();
    }

    public danon.Cymbergaj.Geometry.Point getCenter() {
        danon.Cymbergaj.Geometry.Point point = new danon.Cymbergaj.Geometry.Point(position);
        return point.add(size.getWidth() / 2, size.getHeight() / 2);
    }

    public Shape asAwtShape() {
        return new Rectangle(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }
}
