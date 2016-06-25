package danon.Cymbergaj.View;

import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Rotation;
import danon.Cymbergaj.Geometry.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    private final BufferedImage spriteSheet;
    private final Size spriteSize;
    private final int spritesAmount;
    private int currentSpriteIndex = 0;

    public SpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount) {
        this.spriteSheet = spriteSheet;
        this.spriteSize = spriteSize;
        this.spritesAmount = spritesAmount;
    }

    public void drawOn(Graphics2D canvas, Point position) {
        drawOn(canvas, position, new Rotation());
    }

    public void drawOn(Graphics2D canvas, Point position, Rotation rotation) {
        Point rot = rotation.getCenter();

        canvas.translate(position.x, position.y);
        canvas.rotate(rotation.getAngle().getValue());

        canvas.drawImage(spriteSheet,
                -rot.getX(), -rot.getY(),
                spriteSize.getWidth() - rot.getX(),
                spriteSize.getHeight() - rot.getY(),
                currentSpriteIndex * spriteSize.getWidth(), 0,
                (currentSpriteIndex + 1) * spriteSize.getWidth(),  spriteSize.getHeight(),
                null);

        canvas.rotate(-rotation.getAngle().getValue());
        canvas.translate(-position.x, -position.y);
    }

    public Size getSpriteSize() {
        return spriteSize;
    }

    public void next() {
        currentSpriteIndex = (currentSpriteIndex + 1) % spritesAmount;
    }

    public void setFrame(int index) {
        currentSpriteIndex = (index+spritesAmount*10) % spritesAmount;
    }
}