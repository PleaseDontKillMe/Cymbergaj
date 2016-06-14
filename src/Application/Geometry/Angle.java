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

    public Angle(Angle angle) {
        this.value = angle.getValue();
    }

    public static Angle fromDegrees(double value) {
        Angle angle = new Angle();
        angle.setDegreeValue(value);
        return angle;
    }

    public double toDegrees() {
        return value / Math.PI * 180;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double Value) {
        value = Value;
    }

    public Angle plus(double value) {
        return new Angle(this.value + value);
    }

    public void setDegreeValue(double value) {
        this.value = value / 180 * Math.PI;
    }

    public void flipHorizontal() {
        value = -value;
    }

    public Angle flipOf(Angle angle) {
        return new Angle(2 * angle.getValue() - value);
    }

    public Angle between(Angle angle) {
        double difference = Math.abs(this.value - angle.getValue());

        double newValue;
        if (difference <= Math.PI) {
            newValue = difference;
        } else {
            newValue = Math.abs(difference - 2 * Math.PI);
        }

        return new Angle(newValue).getNormalized();
    }

    public Angle getNormalized() {
        return new Angle(this.value + Math.ceil(-this.toFraction()) * Math.PI * 2);
    }

    public double toFraction() {
        return value / (Math.PI * 2);
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
