package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.View.AnimatedSpriteSheet;

import java.awt.*;


public class FireballRenderer extends BodyRenderer {
    private final AnimatedSpriteSheet spriteSheet;
    private int refreshesToNextFrame = 0;

    public FireballRenderer(Fireball fireball, ImagesRepository images) {
        super(fireball);
        this.spriteSheet = images.fireball.getSpriteSheet();
    }

    @Override
    public void renderBody(Graphics2D canvas) {
        Size size = spriteSheet.getSpriteSize();
        spriteSheet.drawOn(canvas, new Point(-size.getWidth()/2, -size.getHeight()/2));
    }

    @Override
    public void update(double elapsedTime) {
        refreshesToNextFrame++;
        if (refreshesToNextFrame == 8) {
            refreshesToNextFrame = 0;
            spriteSheet.next();
        }
    }
}
