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

    SpriteSheet handgunIdle;
    SpriteSheet handgunMelee;
    SpriteSheet handgunMove;
    SpriteSheet handgunReload;
    SpriteSheet handgunShoot;

    public void load() {
        background = Loader.image("space-background.png");
        fireball = new PackedSpriteSheet(Loader.image("fireball.png"), new Size(64, 64), 8);
        spaceship = new PackedSpriteSheet(Loader.image("spaceship2.png"), new Size(64, 64), 32);

        handgunIdle = new MultifileSpriteSheet("character/handgun/idle/survivor-idle_handgun_", 20).loadImages();
        handgunMelee = new MultifileSpriteSheet("character/handgun/meleeattack/survivor-meleeattack_handgun_", 15).loadImages();
        handgunMove = new MultifileSpriteSheet("character/handgun/move/survivor-move_handgun_", 20).loadImages();
        handgunReload = new MultifileSpriteSheet("character/handgun/reload/survivor-reload_handgun_", 15).loadImages();
        handgunShoot = new MultifileSpriteSheet("character/handgun/shoot/survivor-shoot_handgun_", 3).loadImages();
    }

}
