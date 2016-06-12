package Application.View;

import Application.Geometry.Angle;
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
        drawOn(canvas, position, new Angle());
    }

    public void drawOn(Graphics2D canvas, Point position, Angle rotation) {
        int column = getColumnBasedOnIndex(currentSpriteIndex);
        int row = getRowBasedOnIndex(currentSpriteIndex);

        canvas.translate(position.x,position.y);
        canvas.rotate(rotation.getValue());

        canvas.drawImage(spriteSheet,
                0, 0, spriteSize.getWidth(),spriteSize.getHeight(),
                column * spriteSize.getWidth(), row * spriteSize.getHeight(),
                (column + 1) * spriteSize.getWidth(), (row + 1) * spriteSize.getHeight(),
                null);

        canvas.rotate(-rotation.getValue());
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