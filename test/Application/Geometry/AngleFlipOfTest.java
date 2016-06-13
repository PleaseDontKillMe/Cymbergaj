package Application.Geometry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.LinkedList;

@RunWith(Parameterized.class)
public class AngleFlipOfTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> collection = new LinkedList<>();

        collection.add(new Object[]{0, 90, 180});
        collection.add(new Object[]{0, 45, 90});
        collection.add(new Object[]{0, -45, -90});
        collection.add(new Object[]{180, 0, 180});
        collection.add(new Object[]{45, 45, 45});

        collection.add(new Object[]{90, 90, 90});
        collection.add(new Object[]{180, 90, 0});
        collection.add(new Object[]{90, 0, 180});
        collection.add(new Object[]{0, 360-45, -90});

        return collection;
    }

    private Angle givenAngle;
    private Angle flipOfAngle;
    private Angle expectedAngle;

    public AngleFlipOfTest(float given, float flipOf, float expected) {
        this.givenAngle = Angle.fromDegrees(given);
        this.flipOfAngle = Angle.fromDegrees(flipOf);
        this.expectedAngle = Angle.fromDegrees(expected);
    }

    @Test
    public void testFlipOf() throws Exception {
        // when
        givenAngle.flipOf(flipOfAngle);

        // then
        Assert.assertTrue(givenAngle.equals(expectedAngle));
    }
}