package Application.View;

import Application.Geometry.Size;

import Application.Geometry.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSpriteSheet {
    private final BufferedImage spriteSheet;
    private final Size spriteSize;
    private final int spritesAmount;
    private final int currentSpriteIndex;

    public AnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount) {
        this(spriteSheet, spriteSize, spritesAmount, 0);
    }

    private AnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount, int currentSpriteIndex) {
        this.spriteSheet = spriteSheet;
        this.spriteSize = spriteSize;
        this.spritesAmount = spritesAmount;
        this.currentSpriteIndex = currentSpriteIndex;
    }

    public void drawOn(Graphics2D canvas, Point position) {
        canvas.drawImage(spriteSheet,
                position.getX(), position.getY(),
                position.getX() + spriteSize.getWidth(), position.getY() + spriteSize.getHeight(),
                currentSpriteIndex * spriteSize.getWidth(), 0,
                currentSpriteIndex * spriteSize.getWidth() + spriteSize.getWidth(), spriteSize.getHeight(),
        null);
    }

    public AnimatedSpriteSheet next() {
        int nextSpriteIndex = (currentSpriteIndex + 1) % spritesAmount;
        return new AnimatedSpriteSheet(spriteSheet, spriteSize, spritesAmount, nextSpriteIndex);
    }
}
