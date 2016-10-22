package danon.Cymbergaj.View;


import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Rotation;
import danon.Cymbergaj.Geometry.Size;

import java.awt.*;

public interface SpriteSheet {
    Size getSpriteSize();

    void next();

    void setFrame(int frame);

    default void reset() {
        setFrame(0);
    }

    int getCurrentFrame();

    void drawOn(Graphics2D canvas, Point point);

    void drawOn(Graphics2D canvas, Point point, Rotation rotation);

    Point getAnchorPoint();
}
