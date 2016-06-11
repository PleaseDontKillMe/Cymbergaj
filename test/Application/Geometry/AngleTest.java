package Application.Geometry;

import org.junit.Assert;
import org.junit.Test;

public final class AngleTest {

    @Test
    public void shouldGetValue() {
        // given
        Angle angle = new Angle(20.5);

        // when
        double value = angle.getValue();

        // then
        Assert.assertEquals(value, 20.5, Angle.DELTA);
    }
}
