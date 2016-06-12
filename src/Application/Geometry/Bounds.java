package Application.Geometry;

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
}
