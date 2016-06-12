package Application.View;


import Application.Geometry.Size;

import java.awt.image.BufferedImage;

public class RowedAnimatedSpriteSheet extends AnimatedSpriteSheet {

    private final int columnsCount;

    public RowedAnimatedSpriteSheet(BufferedImage spriteSheet, Size spriteSize, int spritesAmount, int columnsCount) {
        super(spriteSheet, spriteSize, spritesAmount);
        this.columnsCount = columnsCount;
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
