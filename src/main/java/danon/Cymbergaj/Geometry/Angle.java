package danon.Cymbergaj.Geometry;


public final class Angle {
    public static final double DELTA = 1e-5;

    private double value;

    public Angle() {
        this(0);
    }

    public Angle(double value) {
        this.value = value;
    }

    public static Angle fromDegrees(double value) {
        return new Angle(value / 180 * Math.PI);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
