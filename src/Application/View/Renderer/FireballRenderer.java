package Application.View.Renderer;

import Application.Model.World.Character.Fireball;
import Application.View.AnimatedSpriteSheet;

import java.awt.*;

public class FireballRenderer implements Renderer {
    private final Fireball fireball;
    private final AnimatedSpriteSheet spriteSheet;
    private int refreshesToNextFrame = 0;

    public FireballRenderer(Fireball fireball, ImagesRepository images) {
        this.fireball = fireball;
        this.spriteSheet = images.fireball;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        spriteSheet.drawOn(canvas, fireball.getPosition());
        refreshesToNextFrame++;
        if (refreshesToNextFrame == 4){
            refreshesToNextFrame = 0;
            spriteSheet.next();
        }
    }
}
