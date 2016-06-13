package Application.Geometry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.LinkedList;

@RunWith(Parameterized.class)
public class AngleTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> collection = new LinkedList<>();

        collection.add(new Object[]{0, 0, 0});
        collection.add(new Object[]{10, 5, 5});
        collection.add(new Object[]{5, 10, 5});
        collection.add(new Object[]{20, -10, 30});
        collection.add(new Object[]{350, 360, 10});
        collection.add(new Object[]{350, 10, 20});
        collection.add(new Object[]{10, 350, 20});
        collection.add(new Object[]{90, 180, 90});
        collection.add(new Object[]{180, 0, 180});
        collection.add(new Object[]{270, 90, 180});
        collection.add(new Object[]{360, 0, 0});
        collection.add(new Object[]{0, 360, 0});
        collection.add(new Object[]{45, -45, 90});
        collection.add(new Object[]{-45, 45, 90});
        collection.add(new Object[]{720, -10, 10});
        collection.add(new Object[]{720, 350, 10});

        return collection;
    }

    private Angle angle1;
    private Angle angle2;
    private Angle expectedDiff;

    public AngleTest(float degrees1, float degrees2, float diff) {
        this.angle1 = Angle.fromDegrees(degrees1);
        this.angle2 = Angle.fromDegrees(degrees2);
        this.expectedDiff = Angle.fromDegrees(diff);
    }

    @Test
    public void testDiff() throws Exception {
        // when
        Angle diff = angle1.diff(angle2);

        // then
        Assert.assertTrue(diff.equals(expectedDiff));
    }
}