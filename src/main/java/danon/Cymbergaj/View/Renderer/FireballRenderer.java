package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.View.AnimatedSpriteSheet;

import java.awt.*;

public class FireballRenderer implements Renderable {
    private final Fireball fireball;
    private final AnimatedSpriteSheet spriteSheet;
    private int refreshesToNextFrame = 0;

    public FireballRenderer(Fireball fireball, ImagesRepository images) {
        this.fireball = fireball;
        this.spriteSheet = images.fireball.getSpriteSheet();
    }

    @Override
    public void renderOn(Graphics2D canvas) {

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
