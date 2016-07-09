package danon.Cymbergaj.View.Renderer;


import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.View.Loader;
import danon.Cymbergaj.View.MultifileSpriteSheet;
import danon.Cymbergaj.View.PackedSpriteSheet;
import danon.Cymbergaj.View.SpriteSheet;

import java.awt.image.BufferedImage;

public class ImagesRepository {
    BufferedImage background;
    SpriteSheet fireball;
    SpriteSheet spaceship;

    SpriteSheet handgun;

    public void load() {
        background = Loader.image("space-background.png");
        fireball = new PackedSpriteSheet(Loader.image("fireball.png"), new Size(64, 64), 8);
        spaceship = new PackedSpriteSheet(Loader.image("spaceship2.png"), new Size(64, 64), 32);

        handgun = new MultifileSpriteSheet("character/handgun/idle/survivor-idle_handgun_", 20).loadImages();
    }

}
