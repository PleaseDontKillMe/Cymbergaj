package danon.Cymbergaj.Geometry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VectorTest {

    @Test
    public void shouldCreateVector() throws Exception {
        // given
        Point start = new Point(-2, -3);
        Point end = new Point(5, 8);

        // when
        Point vector = Vector.fromTo(start, end);

        // then
        assertEquals(vector.getX(), 7, Angle.DELTA);
        assertEquals(vector.getY(), 11, Angle.DELTA);
    }

}