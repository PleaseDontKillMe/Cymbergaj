package Application.View;

import Application.Geometry.Size;

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

    public void drawOn(Graphics2D canvas, Application.Geometry.Point position) {
        canvas.drawImage(spriteSheet,
                0, currentSpriteIndex * spriteSheet.getWidth(), spriteSheet.getWidth(), spriteSheet.getHeight(),
                position.getX(), position.getY(), spriteSheet.getWidth(), spriteSheet.getHeight(),
                null);
    }

    public AnimatedSpriteSheet next() {
        int nextSpriteIndex = (currentSpriteIndex + 1) % spritesAmount;
        return new AnimatedSpriteSheet(spriteSheet, spriteSize, spritesAmount, nextSpriteIndex);
    }
}
