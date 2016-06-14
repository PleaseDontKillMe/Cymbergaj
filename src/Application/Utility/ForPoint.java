package Application.Utility;

import Application.Geometry.Point;

public class ForPoint {

    private final Point instance;

    public ForPoint(Point instance) {
        this.instance = instance;
    }

    public boolean performEquals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && performEqualsSimilar((Point) o);

    }

    public boolean performEqualsSimilar(Point point) {
        return Compare.doublesEqual(point.x, instance.x, Point.DELTA) &&
                Compare.doublesEqual(point.y, instance.y, Point.DELTA);
    }

    public int generateHashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(instance.x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(instance.y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String performToString() {
        return "Point[x:" + instance.x + ", y:" + instance.y + "]";
    }
}
