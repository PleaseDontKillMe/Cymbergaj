package danon.Cymbergaj.View.Renderer;


import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.World.Control.Spaceship;
import danon.Cymbergaj.View.AnimatedSpriteSheet;

import java.awt.*;


public class SpaceshipRenderer extends BodyRenderer {

    private final Spaceship spaceship;
    private final AnimatedSpriteSheet spriteSheet;

    public SpaceshipRenderer(Spaceship spaceship, ImagesRepository images) {
        super(spaceship);
        this.spaceship = spaceship;
        this.spriteSheet = images.spaceship.getSpriteSheet();
    }

    double totalElapsedTime = 0;

    @Override
    public void update(double elapsedTime) {
        totalElapsedTime += elapsedTime;
        int frame = (int) Math.round(totalElapsedTime * 40.0);
        spriteSheet.setFrame(frame);
    }

    @Override
    public void renderBody(Graphics2D canvas) {
        Size size = spriteSheet.getSpriteSize();
        spriteSheet.drawOn(canvas, new Point(-size.getWidth()/2, -size.getHeight()/2));
    }
}
