package danon.Cymbergaj.View;

import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Rotation;
import danon.Cymbergaj.Geometry.Size;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class MultifileSpriteSheet implements SpriteSheet {
    private final String basePath;
    private final int filesCount;
    private final List<BufferedImage> images = new LinkedList<>();

    public MultifileSpriteSheet(String basePath, int filesCount) {
        this.basePath = basePath;
        this.filesCount = filesCount;
    }

    public void loadImages() {
        for (int i = 0; i < filesCount; i++) {
            images.add(Loader.image(basePath + i + ".png"));
        }
    }

    @Override
    public Size getSpriteSize() {
        return null;
    }

    @Override
    public void next() {

    }

    @Override
    public void setFrame(int frame) {

    }

    @Override
    public void drawOn(Graphics2D canvas, Point point) {

    }

    @Override
    public void drawOn(Graphics2D canvas, Point point, Rotation rotation) {

    }
}
