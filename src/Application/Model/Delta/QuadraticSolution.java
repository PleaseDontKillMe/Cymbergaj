package Application.Model.Delta;

public class QuadraticSolution {
    private double[] solutions;

    QuadraticSolution() {
        this.solutions = new double[]{};
    }

    QuadraticSolution(double x) {
        this.solutions = new double[]{x};
    }

    QuadraticSolution(double x1, double x2) {
        this.solutions = new double[]{x1, x2};
    }

    public double[] getSolutions() {
        return solutions;
    }

    public int count() {
        return solutions.length;
    }

    public boolean hasNone() {
        return solutions.length == 0;
    }

    public boolean hasOne() {
        return solutions.length == 1;
    }

    public boolean hasTwo() {
        return solutions.length == 2;
    }
}