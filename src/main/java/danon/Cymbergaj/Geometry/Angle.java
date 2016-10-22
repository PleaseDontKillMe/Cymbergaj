package danon.Cymbergaj.Geometry;


public final class Angle {
    private double value;

    Angle() {
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

    void setValue(double value) {
        this.value = value;
    }
}
