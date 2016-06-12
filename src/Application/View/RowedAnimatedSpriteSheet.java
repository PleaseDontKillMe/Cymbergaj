package Application.View;


import Application.Geometry.*;

import java.awt.image.BufferedImage;

public class RowedAnimatedSpriteSheet extends AnimatedSpriteSheet {

    private final int columnsCount;

    public RowedAnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount, int columnsCount) {
        super(spriteSheet, spriteSize, spritesAmount);
        this.columnsCount = columnsCount;
    }

    public RowedAnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount, int columnsCount, int currentSpriteIndex) {
        super(spriteSheet, spriteSize, spritesAmount, currentSpriteIndex);
        this.columnsCount = columnsCount;
    }

    @Override
    public AnimatedSpriteSheet next() {
        int nextSpriteIndex = (currentSpriteIndex + 1) % this.spritesAmount;
        return new RowedAnimatedSpriteSheet(this.spriteSheet, this.spriteSize, spritesAmount, columnsCount, nextSpriteIndex);
    }

    @Override
    protected int getColumnBasedOnIndex(int index) {
        return index % columnsCount;
    }

    @Override
    protected int getRowBasedOnIndex(int index) {
        return index / columnsCount;
    }
}
