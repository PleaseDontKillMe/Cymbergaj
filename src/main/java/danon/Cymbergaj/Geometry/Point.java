package danon.Cymbergaj.Geometry;


import danon.Cymbergaj.Utility.Compare;
import org.dyn4j.geometry.Vector2;

public class Point {
    private static final double DELTA = 1e-5;

    public final double x;
    public final double y;

    public Point() {
        this(0, 0);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Vector2 vector) {
        this(vector.x, vector.y);
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    private double distanceTo(Point A) {
        return Math.sqrt(Math.pow(this.x - A.x, 2) + Math.pow(this.y - A.y, 2));
    }

    public Angle angleTo(Point point) {
        if (this.equals(point)) {
            throw new RuntimeException("Cannot calculate angleTo of the same points");
        }

        Angle angle = new Angle(Math.asin((point.y - this.y) / this.distanceTo(point)));

        if (point.x < this.x) {
            angle.setValue(Math.PI - angle.getValue());
        }

        return angle;
    }

    private boolean equals(Point point) {
        return Compare.doublesEqual(point.x, x, DELTA) && Compare.doublesEqual(point.y, y, DELTA);
    }

    @Override
    public String toString() {
        return String.valueOf((int)this.x) + '/' + String.valueOf((int)this.y);
    }
}
