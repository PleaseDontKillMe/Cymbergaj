package Application.View.Renderer;

import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Geometry.Rotation;
import Application.Model.World.Character.Fireball;
import Application.View.AnimatedSpriteSheet;

import java.awt.*;

public class FireballRenderer implements Renderer {
    private final Fireball fireball;
    private final AnimatedSpriteSheet spriteSheet;
    private int refreshesToNextFrame = 0;

    public FireballRenderer(Fireball fireball, ImagesRepository images) {
        this.fireball = fireball;
        this.spriteSheet = images.fireball.getSpriteSheet();
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        Angle angle = Angle.fromDegrees(180 + fireball.getDirection().toDegrees());
        Point position = fireball.getPosition();
        spriteSheet.drawOn(canvas, position, new Rotation(angle, new Point(8, 32)));
        canvas.drawString(position.toString() + "  " + fireball.getDirection().toDegrees(), position.getX(), position.getY());
    }

    @Override
    public void update() {
        refreshesToNextFrame++;
        if (refreshesToNextFrame == 8) {
            refreshesToNextFrame = 0;
            spriteSheet.next();
        }
    }
}
