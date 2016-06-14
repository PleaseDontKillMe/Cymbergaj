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

    public double getValue() {
        return value;
    }

    public void setValue(double Value) {
        value = Value;
    }

    public double toDegrees() {
        return value / Math.PI * 180;
    }

    public void setDegreeValue(double value) {
        this.value = value / 180 * Math.PI;
    }

    public void clockwise(double radians) {
        this.value += radians;
    }

    public void flipHorizontal() {
        value = -value;
    }

    public void flipOf(Angle angle) {
        value += angle.getValue() * 2;
        while (value > 2 * Math.PI) {
            value -= 2 * Math.PI;
        }
    }

    public Angle diff(Angle angle) {
        double difference = Math.abs(this.value - angle.getValue());

        double newValue;
        if (difference <= Math.PI) {
            newValue = difference;
        } else {
            newValue = Math.abs(difference - 2 * Math.PI);
        }

        while (newValue > 2 * Math.PI) {
            newValue -= 2 * Math.PI;
        }
        return new Angle(newValue);
    }

    public Angle getNormalized() {
        return new Angle(this.value + Math.ceil(-this.toFraction()) * Math.PI*2);
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
