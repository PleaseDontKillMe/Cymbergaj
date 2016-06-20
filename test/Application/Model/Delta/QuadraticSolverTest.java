package Application.Model.Delta;


import Application.Geometry.Angle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.LinkedList;

@RunWith(Parameterized.class)
public class QuadraticSolverTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> collection = new LinkedList<>();

        collection.add(new Object[]{1, 1, 1, sol()});
        collection.add(new Object[]{1, 0, 0, sol(0)});
        collection.add(new Object[]{1, 0, -4, sol(-2, 2)});

        return collection;
    }

    private double a, b, c;
    private double[] expectedSolutions;

    public QuadraticSolverTest(double a, double b, double c, double[] solutions) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expectedSolutions = solutions;
    }

    @Test
    public void shouldSolveDelta() throws Exception {
        // given
        QuadraticSolver solver = new QuadraticSolver(a, b, c);

        // when
        QuadraticSolution solution = solver.solve();

        // then
        Assert.assertArrayEquals(solution.getSolutions(), expectedSolutions, Angle.DELTA);
    }

    static double[] sol(double... solution) {
        return solution;
    }
}