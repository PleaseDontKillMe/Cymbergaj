package Application.Geometry;

public class Vector {
    public static Point fromTo(Point start, Point end) {
        return new Point(end.diff(start));
    }
}
