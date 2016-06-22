package danon.Cymbergaj.Geometry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.LinkedList;

@RunWith(Parameterized.class)
public class PointAngleToTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> collection = new LinkedList<>();

        collection.add(new Object[]{0,0, 1,1, 45});
        collection.add(new Object[]{1,1, 0,0, 225});
        collection.add(new Object[]{1,1, 1,0, 270});
        collection.add(new Object[]{2,2, 2,0, 270});

        collection.add(new Object[]{2,0, 0,2, 135});
        collection.add(new Object[]{2,2, 0,2, 180});

        return collection;
    }

    private Point startPoint;
    private Point endPoint;
    private Angle expectedAngle;

    public PointAngleToTest(float x1, float y1, float x2, float y2, float expectedAngle) {
        this.startPoint = new Point(x1, y1);
        this.endPoint = new Point(x2, y2);
        this.expectedAngle = Angle.fromDegrees(expectedAngle).getNormalized();
    }

    @Test(expected=RuntimeException.class)
    public void testValidInput() throws Exception {
        // when
        new Point(2, 1).angleTo(new Point(2, 1));
    }

    @Test
    public void testAngle() throws Exception {
        // when
        Angle result = startPoint.angleTo(endPoint);

        // then
        Assert.assertEquals(expectedAngle.toDegrees(), result.getNormalized().toDegrees(), Angle.DELTA);
    }
}