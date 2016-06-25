package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Geometry.Angle;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Rotation;
import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.View.AnimatedSpriteSheet;
import org.dyn4j.geometry.Vector2;

import java.awt.*;


public class FireballRenderer extends BodyRenderer {

    private final Fireball fireball;
    private final AnimatedSpriteSheet spriteSheet;
    private int refreshesToNextFrame = 0;
    private Angle direction = Angle.fromDegrees(180);

    public FireballRenderer(Fireball fireball, ImagesRepository images) {
        super(fireball);
        this.fireball = fireball;
        this.spriteSheet = images.fireball.getSpriteSheet();
    }

    @Override
    public void renderBody(Graphics2D canvas) {
        Size size = spriteSheet.getSpriteSize();
        Vector2 vector = fireball.getLinearVelocity();
        if (!vector.equals(0,0)) {
            direction = new Point(vector).angleTo(new Point());
        }

        spriteSheet.drawOn(canvas, new Point(-size.getWidth()/2 + 32, -size.getHeight()/2 + 32), new Rotation(direction, new Point(10,32)));
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
