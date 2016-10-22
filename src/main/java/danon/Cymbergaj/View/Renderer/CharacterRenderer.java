package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Geometry.*;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Model.Updatable;
import danon.Cymbergaj.View.SpriteSheet;

import java.awt.*;

public class CharacterRenderer implements Renderer, Updatable {
    private final SpriteSheet spriteSheet;

    private double totalElapsedTime = 0;

    public CharacterRenderer(ImagesRepository imagesRepository) {
        this.spriteSheet = imagesRepository.handgunReload;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        Size size = spriteSheet.getSpriteSize();
        spriteSheet.drawOn(canvas, new Point(-size.getWidth() / 2, -size.getHeight() / 2));
    }

    @Override
    public void update(double elapsedTime) {
        totalElapsedTime += elapsedTime;
        int frame = (int) Math.round(totalElapsedTime * 20.0);
        spriteSheet.setFrame(frame);
    }
}
