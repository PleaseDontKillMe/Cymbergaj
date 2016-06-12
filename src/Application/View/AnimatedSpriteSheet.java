package Application.View;

import Application.Geometry.Rotation;
import Application.Geometry.Size;

import Application.Geometry.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSpriteSheet {
    private final BufferedImage spriteSheet;
    private final Size spriteSize;
    private final int spritesAmount;
    private final int columnsCount;
    private int currentSpriteIndex = 0;

    public AnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount, int columnsCount) {
        this.spriteSheet = spriteSheet;
        this.spriteSize = spriteSize;
        this.spritesAmount = spritesAmount;
        this.columnsCount = columnsCount;
    }

    public void drawOn(Graphics2D canvas, Point position) {
        drawOn(canvas, position, new Rotation());
    }

    public void drawOn(Graphics2D canvas, Point position, Rotation rotation) {
        int column = getColumnBasedOnIndex(currentSpriteIndex);
        int row = getRowBasedOnIndex(currentSpriteIndex);

        Point rot = rotation.getCenter();

        canvas.translate(position.x, position.y);
        canvas.rotate(rotation.getAngle().getValue());

        canvas.drawImage(spriteSheet,
                -rot.getX(), -rot.getY(),
                spriteSize.getWidth()-rot.getX(),
                spriteSize.getHeight()-rot.getY(),
                column * spriteSize.getWidth(), row * spriteSize.getHeight(),
                (column + 1) * spriteSize.getWidth(), (row + 1) * spriteSize.getHeight(),
                null);

        canvas.rotate(-rotation.getAngle().getValue());
        canvas.translate(-position.x, -position.y);
    }

    public void next() {
        currentSpriteIndex = (currentSpriteIndex + 1) % spritesAmount;
    }

    public boolean isLast() {
        return currentSpriteIndex == (spritesAmount - 1);
    }

    protected int getColumnBasedOnIndex(int index) {
        return index % columnsCount;
    }

    protected int getRowBasedOnIndex(int index) {
        return index / columnsCount;
    }
}