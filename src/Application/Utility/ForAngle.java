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
        return this == o || !(o == null || getClass() != o.getClass()) && performEqualsSimilar((Angle) o);

    }

    public int generateHashCode() {
        long temp = Double.doubleToLongBits(instance.getValue());
        return (int) (temp ^ (temp >>> 32));
    }

    public String performToString() {
        return "Angle[angleTo:" + instance.getValue() + "]";
    }
}
