package danon.Cymbergaj.View;

import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Rotation;
import danon.Cymbergaj.Geometry.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PackedSpriteSheet implements SpriteSheet {

    private final BufferedImage spriteSheet;
    private final Size spriteSize;
    private final int spritesAmount;
    private int currentSpriteIndex = 0;

    public PackedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount) {
        this.spriteSheet = spriteSheet;
        this.spriteSize = spriteSize;
        this.spritesAmount = spritesAmount;
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

    @Override
    public Size getSpriteSize() {
        return spriteSize;
    }

    @Override
    public void next() {
        currentSpriteIndex = (currentSpriteIndex + 1) % spritesAmount;
    }

    @Override
    public void setFrame(int index) {
        currentSpriteIndex = (index+spritesAmount*10) % spritesAmount;
    }
}