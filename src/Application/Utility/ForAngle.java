package Application.Utility;

import Application.Geometry.Angle;

public class ForAngle {

    private Angle instance;

    public ForAngle(Angle angle) {
        this.instance = angle;
    }

    public boolean performEqualsSimilar(Angle angle) {
        return Compare.doublesEqual(angle.getValue(), instance.getValue(), Angle.DELTA);
    }

    public boolean performEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return performEqualsSimilar((Angle) o);
    }

    public int generateHashCode() {
        long temp = Double.doubleToLongBits(instance.getValue());
        return (int) (temp ^ (temp >>> 32));
    }

    public String performToString() {
        return "Angle[angle:" + instance.getValue() + "]";
    }
}
