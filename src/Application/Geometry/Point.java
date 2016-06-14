package Application.Geometry;

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

    public int getX(int factor) {
        return (int) Math.ceil(this.x * factor);
    }

    public int getY(int factor) {
        return (int) Math.ceil(this.y * factor);
    }

    public void setSize(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point add(Point a) {
        return new Point(this.x + a.x, this.y + a.y);
    }

    public Point add(double x, double y) {
        return new Point(this.x + x, this.y + y);
    }

    public Point sub(Point a) {
        return new Point(this.x - a.x, this.y - a.y);
    }

    public Point sub(double x, double y) {
        return new Point(this.x - x, this.y - y);
    }

    public Point find(double distance, Angle angle) {
        return new Point(
                this.x + distance * Math.cos(angle.getValue()),
                this.y + distance * Math.sin(angle.getValue())
        );
    }

    public Point negY() {
        return new Point(this.x, -this.y);
    }

    public boolean stepTo(Point p, double distance) {
        Angle dir = this.angle(p);
        if (this.distanceTo(p) > distance) {
            this.setSize(this.find(distance, dir));
            return false;
        } else {
            this.setSize(p);
            return true;
        }
    }

    public double distanceTo(Point A) {
        return Math.sqrt(Math.pow(this.x - A.x, 2) + Math.pow(this.y - A.y, 2));
    }

    public Angle angle(Point point) {
        if (this.equals(point)) {
            throw new RuntimeException("Cannot calculate angle of the same points");
        }

        Angle angle = new Angle(Math.asin((point.y - this.y) / this.distanceTo(point)));

        if (point.x < this.x) {
            angle.setValue(Math.PI - angle.getValue());
        }

        return angle;
    }

    public void scale(double factor) {
        this.x *= factor;
        this.y *= factor;
    }

    public Point copy() {
        return new Point(x, y);
    }

    public boolean equals(Point point) {
        return new Application.Utility.ForPoint(this).performEqualsSimilar(point);
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        return new Application.Utility.ForPoint(this).performEquals(o);
    }

    @Override
    public int hashCode() {
        return new Application.Utility.ForPoint(this).generateHashCode();
    }

    @Override
    public String toString() {
        return new Application.Utility.ForPoint(this).performToString();
    }
}
