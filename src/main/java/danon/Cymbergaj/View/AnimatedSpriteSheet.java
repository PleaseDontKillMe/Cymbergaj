package danon.Cymbergaj.View;

import danon.Cymbergaj.Geometry.Rotation;
import danon.Cymbergaj.Geometry.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSpriteSheet {

    private final BufferedImage spriteSheet;
    private final Size spriteSize;
    private final int spritesAmount;
    private final int columnsCount;
    private int currentSpriteIndex = 0;

    public AnimatedSpriteSheet(SpriteSheetDefinition definition) {
        this(definition.spriteSheet, definition.spriteSize, definition.spritesAmount, definition.columnsCount);
    }

    private AnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount, int columnsCount) {
        this.spriteSheet = spriteSheet;
        this.spriteSize = spriteSize;
        this.spritesAmount = spritesAmount;
        this.columnsCount = columnsCount;
    }

    public void drawOn(Graphics2D canvas, danon.Cymbergaj.Geometry.Point position) {
        drawOn(canvas, position, new Rotation());
    }

    public void drawOn(Graphics2D canvas, danon.Cymbergaj.Geometry.Point position, Rotation rotation) {
        int column = currentSpriteIndex % columnsCount;
        int row = currentSpriteIndex / columnsCount;

        danon.Cymbergaj.Geometry.Point rot = rotation.getCenter();

        canvas.translate(position.x, position.y);
        canvas.rotate(rotation.getAngle().getValue());

        canvas.drawImage(spriteSheet,
                -rot.getX(), -rot.getY(),
                spriteSize.getWidth() - rot.getX(),
                spriteSize.getHeight() - rot.getY(),
                column * spriteSize.getWidth(), row * spriteSize.getHeight(),
                (column + 1) * spriteSize.getWidth(), (row + 1) * spriteSize.getHeight(),
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