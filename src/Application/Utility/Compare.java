package Application.Utility;

class Compare {

    public static boolean doublesEqual(double d1, double d2, double delta) {
        return Double.compare(d1, d2) == 0 || Math.abs(d1 - d2) <= delta;
    }
}
