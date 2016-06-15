package Application.Geometry;


import Application.Utility.Compare;

public class Point {
    public static final double DELTA = 1e-5;

    public double x;
    public double y;

    public Point() {
        this(0, 0);
    }

    public Point(Point p) {
        this(p.x, p.y);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public void setSize(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point add(double x, double y) {
        return new Point(this.x + x, this.y + y);
    }

    public Point find(double distance, Angle angle) {
        return new Point(
                this.x + distance * Math.cos(angle.getValue()),
                this.y + distance * Math.sin(angle.getValue())
        );
    }

    public double distanceTo(Point A) {
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

    public boolean equals(Point point) {
        return Compare.doublesEqual(point.x, x, DELTA) && Compare.doublesEqual(point.y, y, DELTA);
    }

    @Override
    public String toString() {
        return String.valueOf((int)this.x) + '/' + String.valueOf((int)this.y);
    }
}
