package Application.View;

import Application.Geometry.Size;

import Application.Geometry.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSpriteSheet {
    protected final BufferedImage spriteSheet;
    protected final Size spriteSize;
    protected final int spritesAmount;
    protected final int currentSpriteIndex;

    public AnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount) {
        this(spriteSheet, spriteSize, spritesAmount, 0);
    }

    protected AnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount, int currentSpriteIndex) {
        this.spriteSheet = spriteSheet;
        this.spriteSize = spriteSize;
        this.spritesAmount = spritesAmount;
        this.currentSpriteIndex = currentSpriteIndex;
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

    public AnimatedSpriteSheet next() {
        int nextSpriteIndex = (currentSpriteIndex + 1) % spritesAmount;
        return new AnimatedSpriteSheet(spriteSheet, spriteSize, spritesAmount, nextSpriteIndex);
    }

    protected int getColumnBasedOnIndex(int index) {
        return index;
    }

    protected int getRowBasedOnIndex(int index) {
        return 0;
    }
}