package danon.Cymbergaj.Geometry;

import org.junit.Assert;
import org.junit.Test;

public class AngleTest {

    @Test
    public void fromDegrees() {
        // given
        double degrees = 180;

        // when
        Angle angle = Angle.fromDegrees(degrees);

        // then
        Assert.assertEquals(Math.PI, angle.getValue(), 1e-7);
    }

    @Test
    public void getValue() {
        // given
        Angle angle = new Angle(Math.PI);

        // when
        double value = angle.getValue();

        // then
        Assert.assertEquals(Math.PI, value, 1e-7);
    }

    @Test
    public void setValue() {
        // given
        Angle angle = new Angle();

        // when
        angle.setValue(Math.PI);

        // then
        Assert.assertEquals(Math.PI, angle.getValue(), 1e-7);
    }
}