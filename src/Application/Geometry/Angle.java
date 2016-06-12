package Application.Geometry;

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
        Angle angle = new Angle();
        angle.setDegreeValue(value);
        return angle;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double Value) {
        value = Value;
    }

    public double toDegrees() {
        return value / Math.PI * 180;
    }

    public double toDec() {
        return value / (Math.PI * 2);
    }

    public void setDegreeValue(double value) {
        this.value = value / 180 * Math.PI;
    }

    public Angle copy() {
        return new Angle(value);
    }

    public boolean equals(Angle angle) {
        return new Application.Utility.ForAngle(this).performEqualsSimilar(angle);
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object object) {
        return new Application.Utility.ForAngle(this).performEquals(object);
    }

    @Override
    public int hashCode() {
        return new Application.Utility.ForAngle(this).generateHashCode();
    }

    @Override
    public String toString() {
        return new Application.Utility.ForAngle(this).performToString();
    }
}
