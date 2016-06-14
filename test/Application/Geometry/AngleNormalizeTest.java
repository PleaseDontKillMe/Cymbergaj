package Application.Geometry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.LinkedList;

@RunWith(Parameterized.class)
public class AngleNormalizeTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> collection = new LinkedList<>();

        collection.add(new Object[]{-720, 0});
        collection.add(new Object[]{-360, 0});
        collection.add(new Object[]{0, 0});
        collection.add(new Object[]{360, 0});
        collection.add(new Object[]{720, 0});

        collection.add(new Object[]{45, 45});
        collection.add(new Object[]{90, 90});
        collection.add(new Object[]{180, 180});

        collection.add(new Object[]{405, 45});
        collection.add(new Object[]{-90, 270});
        collection.add(new Object[]{-15, 345});

        return collection;
    }

    private Angle givenAngle;
    private Angle expectedAngle;

    public AngleNormalizeTest(float given,  float expected) {
        this.givenAngle = Angle.fromDegrees(given);
        this.expectedAngle = Angle.fromDegrees(expected);
    }

    @Test
    public void testNormalize() throws Exception {
        // when
        Angle result = givenAngle.getNormalized();

        // then
        Assert.assertEquals(result.getValue(), expectedAngle.getValue(), Angle.DELTA);
    }
}