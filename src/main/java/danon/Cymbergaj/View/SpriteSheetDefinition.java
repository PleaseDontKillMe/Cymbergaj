package danon.Cymbergaj.View;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.View.AnimatedSpriteSheet;

import java.awt.image.BufferedImage;

public class SpriteSheetDefinition {
    public final BufferedImage spriteSheet;
    public final Size spriteSize;
    public final int spritesAmount;
    public final int columnsCount;

    public SpriteSheetDefinition(BufferedImage spriteSheet, Size spriteSize, int spritesAmount, int columnsCount) {
        this.spriteSheet = spriteSheet;
        this.spriteSize = spriteSize;
        this.spritesAmount = spritesAmount;
        this.columnsCount = columnsCount;
    }

    public AnimatedSpriteSheet getSpriteSheet() {
        return new AnimatedSpriteSheet(this);
    }
}