package danon.Cymbergaj.Model.Delta;


public class QuadraticSolver {
    private final double a;
    private final double b;
    private final double c;

    public QuadraticSolver(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public QuadraticSolution solve() {
        double delta = b * b - 4 * a * c;
        if (delta < 0) {
            return new QuadraticSolution();
        }
        if (delta == 0) {
            return new QuadraticSolution(-b / (2 * a));
        }
        double deltaSqrt = Math.sqrt(delta);
        return new QuadraticSolution((-b - deltaSqrt) / (2 * a), (-b + deltaSqrt) / (2 * a));
    }
}

