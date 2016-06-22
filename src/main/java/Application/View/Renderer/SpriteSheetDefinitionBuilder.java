package Application.View.Renderer;

import Application.Geometry.Size;

import java.awt.image.BufferedImage;

class SpriteSheetDefinitionBuilder {
    private BufferedImage spriteSheet;
    private Size spriteSize;
    private Integer spritesAmount;
    private Integer columnsCount;

    public SpriteSheetDefinitionBuilder image(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
        return this;
    }

    public SpriteSheetDefinitionBuilder size(Size spriteSize) {
        this.spriteSize = spriteSize;
        return this;
    }

    public SpriteSheetDefinitionBuilder amount(int spritesAmount) {
        this.spritesAmount = spritesAmount;
        this.columnsCount = spritesAmount;
        return this;
    }

    public SpriteSheetDefinitionBuilder columns(Integer columnsCount) {
        this.columnsCount = columnsCount;
        return this;
    }

    public SpriteSheetDefinition buildDefinition() {
        return new SpriteSheetDefinition(spriteSheet, spriteSize, spritesAmount, columnsCount);
    }
}