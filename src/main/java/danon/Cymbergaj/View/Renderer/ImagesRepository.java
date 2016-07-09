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

    SpriteSheet knifeIdle;
    SpriteSheet knifeMelee;
    SpriteSheet knifeMove;

    public void load() {
        background = Loader.image("space-background.png");
        fireball = new PackedSpriteSheet(Loader.image("fireball.png"), new Size(64, 64), 8);
        spaceship = new PackedSpriteSheet(Loader.image("spaceship2.png"), new Size(64, 64), 32);

        handgunIdle = new MultifileSpriteSheet("character/handgun/idle/survivor-idle_handgun_", 20).loadImages();
        handgunMelee = new MultifileSpriteSheet("character/handgun/meleeattack/survivor-meleeattack_handgun_", 15).loadImages();
        handgunMove = new MultifileSpriteSheet("character/handgun/move/survivor-move_handgun_", 20).loadImages();
        handgunReload = new MultifileSpriteSheet("character/handgun/reload/survivor-reload_handgun_", 15).loadImages();
        handgunShoot = new MultifileSpriteSheet("character/handgun/shoot/survivor-shoot_handgun_", 3).loadImages();

        knifeIdle = new MultifileSpriteSheet("character/knife/idle/survivor-idle_knife_", 20).loadImages();
        knifeMelee = new MultifileSpriteSheet("character/knife/meleeattack/survivor-meleeattack_knife_", 15).loadImages();
        knifeMove = new MultifileSpriteSheet("character/knife/move/survivor-move_knife_", 20).loadImages();
    }

}
