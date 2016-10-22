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
    private final Point anchorPoint;

    private Size firstFrameSize;
    private int currentIndex = 0;

    public MultifileSpriteSheet(String basePath, int filesCount) {
        this(basePath, filesCount, new Point(116, 120));
    }

    public MultifileSpriteSheet(String basePath, int filesCount, Point anchorPoint) {
        this.basePath = basePath;
        this.filesCount = filesCount;
        this.anchorPoint = anchorPoint;
    }

    public MultifileSpriteSheet loadImages() {
        for (int i = 0; i < filesCount; i++) {
            images.add(Loader.image(basePath + i + ".png"));
        }
        BufferedImage first = images.get(0);
        firstFrameSize = new Size(first.getWidth(), first.getHeight());
        return this;
    }

    @Override
    public Size getSpriteSize() {
        return firstFrameSize;
    }

    @Override
    public void next() {
        currentIndex = currentIndex + 1 % filesCount;
    }

    @Override
    public void setFrame(int frame) {
        currentIndex = frame % filesCount;
    }

    @Override
    public int getCurrentFrame() {
        return currentIndex;
    }

    @Override
    public void drawOn(Graphics2D canvas, Point position) {
        drawOn(canvas, position, new Rotation());
    }

    @Override
    public void drawOn(Graphics2D canvas, Point position, Rotation rotation) {
        Point rot = rotation.getCenter();

        canvas.translate(position.x, position.y);
        canvas.rotate(rotation.getAngle().getValue());

        BufferedImage image = images.get(currentIndex);
        canvas.drawImage(image,
                -rot.getX(), -rot.getY(),
                image.getWidth(),
                image.getHeight(),
                null);

        canvas.rotate(-rotation.getAngle().getValue());
        canvas.translate(-position.x, -position.y);
    }

    @Override
    public Point getAnchorPoint() {
        return anchorPoint;
    }
}
