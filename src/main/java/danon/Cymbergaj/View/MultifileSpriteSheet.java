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

    private Size firstFrameSize;
    private int currentIndex = 0;

    public MultifileSpriteSheet(String basePath, int filesCount) {
        this.basePath = basePath;
        this.filesCount = filesCount;
    }

    public void loadImages() {
        for (int i = 0; i < filesCount; i++) {
            images.add(Loader.image(basePath + i + ".png"));
        }
        BufferedImage first = images.get(0);
        firstFrameSize = new Size(first.getWidth(), first.getHeight());
    }

    @Override
    public Size getSpriteSize() {
        return firstFrameSize;
    }

    @Override
    public void next() {
        currentIndex = currentIndex+1 % filesCount;
    }

    @Override
    public void setFrame(int frame) {
        currentIndex = frame % filesCount;
    }

    @Override
    public void drawOn(Graphics2D canvas, Point point) {

    }

    @Override
    public void drawOn(Graphics2D canvas, Point point, Rotation rotation) {

    }
}
