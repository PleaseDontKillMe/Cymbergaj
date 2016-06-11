package Application.Geometry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class PointTest {

    // TODO move dataProvider to ForPoint class, and leave Point tests clean and single ran

    @Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> collection = new LinkedList<>();

        collection.add(new Object[] {Angle.fromDegrees(0), new Point(25, 10), 10});
        collection.add(new Object[] {Angle.fromDegrees(90), new Point(15, 20), 10});
        collection.add(new Object[] {Angle.fromDegrees(180), new Point(5, 10), 10});
        collection.add(new Object[] {Angle.fromDegrees(270), new Point(15, 0), 10});

        collection.add(new Object[] {Angle.fromDegrees(0), new Point(15, 10), 0});
        collection.add(new Object[] {Angle.fromDegrees(2), new Point(15, 10), 0});
        collection.add(new Object[] {Angle.fromDegrees(15.5), new Point(15, 10), 0});
        collection.add(new Object[] {Angle.fromDegrees(45.5), new Point(15, 10), 0});

        collection.add(new Object[] {Angle.fromDegrees(7), new Point(15.992546, 10.121869), 1});
        collection.add(new Object[] {Angle.fromDegrees(130), new Point(12.750243, 12.681155), 3.5});
        collection.add(new Object[] {Angle.fromDegrees(200), new Point(-0.974774, 4.185657), 17});
        collection.add(new Object[] {Angle.fromDegrees(360), new Point(35, 10), 20});

        return collection;
    }

    private Angle angle;
    private Point expectedResult;
    private double distance;

    public PointTest(Angle angle, Point expectedResult, double distance) {
        this.angle = angle;
        this.expectedResult = expectedResult;
        this.distance = distance;
    }

    @Test
    public void shouldGetValues() {
        // given
        Point point = new Point(69, 123);

        // when
        float x = point.getX();
        float y = point.getY();

        // then
        Assert.assertEquals(69, x, Point.DELTA);
        Assert.assertEquals(123, y, Point.DELTA);
    }

    @Test
    public void shouldFindPointOfAngleAndDistance() {
        // given
        Point startPoint = new Point(15, 10);

        // when
        Point endPoint = startPoint.find(distance, angle);

        // then
        Assert.assertTrue(endPoint.equals(expectedResult));
    }

    @Test
    public void shouldCalculateDistance() {
        // given
        Point a = new Point(1,1);
        Point b = new Point(4, 5);

        // when
        double distance = a.distanceTo(b);

        // then
        Assert.assertEquals(5, distance, 0.00001);
    }

    @Test
    public void shouldCalculateSquaredDistance() {
        // given
        Point a = new Point(1,1);
        Point b = new Point(4, 5);

        // when
        double distance = a.distancePowTo(b);

        // then
        Assert.assertEquals(25, distance, 0.00001);
    }

    @Test
    public void shouldCopyPoint() {
        // given
        Point point = new Point(69, 123);

        // when
        Point copied = point.copy();

        // then
        Assert.assertFalse(copied == point);
        Assert.assertTrue(point.equals(copied));
    }

    @Test
    public void shouldRenderAsString() {
        // given
        Point point = new Point(69, 123);

        // when
        String string = point.toString();

        // then
        Assert.assertEquals("Point[x:69.0, y:123.0]", string);
    }

}