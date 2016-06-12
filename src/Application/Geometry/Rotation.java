package Application.Geometry;

public class Rotation {
    private final Angle angle;
    private final Point center;

    public Rotation(Angle angle) {
        this.angle = angle;
        this.center = new Point();
    }

    public Rotation(Angle angle, Point center) {
        this.angle = angle;
        this.center = center;
    }

    public Angle getAngle() {
        return angle;
    }

    public Point getCenter() {
        return center;
    }
}
