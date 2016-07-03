package danon.Cymbergaj.View.Renderer;


import danon.Cymbergaj.Application;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.Updatable;
import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.View.SpriteSheet;
import org.dyn4j.geometry.Vector2;

import java.awt.*;


public class SpaceshipRenderer extends BodyRenderer implements Updatable {

    private final SpriteSheet spriteSheet;
    private final Spaceship spaceship;

    public SpaceshipRenderer(Spaceship spaceship, ImagesRepository images) {
        super(spaceship);
        this.spaceship = spaceship;
        this.spriteSheet = images.spaceship;
    }

    private double totalElapsedTime = 0;

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

        String keys = spaceship.getKeys().toString();
        Vector2 trans = spaceship.getTransform().getTranslation();
        canvas.drawString(keys, (int)(Application.SCALE * trans.x), (int)(Application.SCALE *trans.y));
    }
}
