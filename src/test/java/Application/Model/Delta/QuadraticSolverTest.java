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

    @Test
    public void shouldGetNone() throws Exception {
        // given
        QuadraticSolution solution = new QuadraticSolution();

        // when
        boolean hasNone = solution.hasNone();

        // then
        Assert.assertTrue(hasNone);
    }

    @Test
    public void shouldGetFirst() throws Exception {
        // given
        QuadraticSolution solution = new QuadraticSolution(2);

        // when
        boolean hasOne = solution.hasOne();
        double first = solution.getFirst();

        // then
        Assert.assertTrue(hasOne);
        Assert.assertEquals(2, first, Angle.DELTA);
    }

    @Test
    public void shouldGetFirstAndSecond() throws Exception {
        // given
        QuadraticSolution solution = new QuadraticSolution(2, 3);

        // when
        boolean hasTwo = solution.hasTwo();
        double first = solution.getFirst();
        double second = solution.getSecond();

        // then
        Assert.assertTrue(hasTwo);
        Assert.assertEquals(2, first, Angle.DELTA);
        Assert.assertEquals(3, second, Angle.DELTA);
    }

    private static double[] sol(double... solution) {
        return solution;
    }
}