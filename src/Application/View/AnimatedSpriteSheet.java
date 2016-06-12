package Application.View;

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

    public AnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount) {
        this(spriteSheet, spriteSize, spritesAmount, spritesAmount);
    }

    protected AnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount, int columnsCount) {
        this.spriteSheet = spriteSheet;
        this.spriteSize = spriteSize;
        this.spritesAmount = spritesAmount;
        this.columnsCount = columnsCount;
    }

    public void drawOn(Graphics2D canvas, Point position) {
        int column = getColumnBasedOnIndex(currentSpriteIndex);
        int row = getRowBasedOnIndex(currentSpriteIndex);

        canvas.drawImage(spriteSheet,
                position.getX(), position.getY(),
                position.getX() + spriteSize.getWidth(), position.getY() + spriteSize.getHeight(),
                column * spriteSize.getWidth(), row * spriteSize.getHeight(),
                (column + 1) * spriteSize.getWidth(), (row + 1) * spriteSize.getHeight(),
                null);
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