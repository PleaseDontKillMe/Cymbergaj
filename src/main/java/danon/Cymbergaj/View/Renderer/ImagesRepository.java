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

    SpriteSheet feetIdle;
    SpriteSheet feetRun;
    SpriteSheet feetWalk;
    SpriteSheet feetStrafeLeft;
    SpriteSheet feetStrafeRight;

    SpriteSheet handgunIdle;
    SpriteSheet handgunMelee;
    SpriteSheet handgunMove;
    SpriteSheet handgunReload;
    SpriteSheet handgunShoot;

    SpriteSheet knifeIdle;
    SpriteSheet knifeMelee;
    SpriteSheet knifeMove;

    SpriteSheet flashlightIdle;
    SpriteSheet flashlightMelee;
    SpriteSheet flashlightMove;

    SpriteSheet rifleIdle;
    SpriteSheet rifleMelee;
    SpriteSheet rifleMove;
    SpriteSheet rifleReload;
    SpriteSheet rifleShoot;

    SpriteSheet shotgunIdle;
    SpriteSheet shotgunMelee;
    SpriteSheet shotgunMove;
    SpriteSheet shotgunReload;
    SpriteSheet shotgunShoot;

    public void load() {
        background = Loader.image("space-background.png");
        fireball = new PackedSpriteSheet(Loader.image("fireball.png"), new Size(64, 64), 8);
        spaceship = new PackedSpriteSheet(Loader.image("spaceship2.png"), new Size(64, 64), 32);

        feetIdle = new MultifileSpriteSheet("character/feet/idle/survivor-idle_", 1).loadImages();
        feetRun = new MultifileSpriteSheet("character/feet/run/survivor-run_", 20).loadImages();
        feetWalk = new MultifileSpriteSheet("character/feet/walk/survivor-walk_", 20).loadImages();
        feetStrafeLeft = new MultifileSpriteSheet("character/feet/strafe_left/survivor-strafe_left_", 20).loadImages();
        feetStrafeRight = new MultifileSpriteSheet("character/feet/strafe_right/survivor-strafe_right_", 20).loadImages();

        handgunIdle = new MultifileSpriteSheet("character/handgun/idle/survivor-idle_handgun_", 20).loadImages();
        handgunMelee = new MultifileSpriteSheet("character/handgun/meleeattack/survivor-meleeattack_handgun_", 15).loadImages();
        handgunMove = new MultifileSpriteSheet("character/handgun/move/survivor-move_handgun_", 20).loadImages();
        handgunReload = new MultifileSpriteSheet("character/handgun/reload/survivor-reload_handgun_", 15).loadImages();
        handgunShoot = new MultifileSpriteSheet("character/handgun/shoot/survivor-shoot_handgun_", 3).loadImages();

        knifeIdle = new MultifileSpriteSheet("character/knife/idle/survivor-idle_knife_", 20).loadImages();
        knifeMelee = new MultifileSpriteSheet("character/knife/meleeattack/survivor-meleeattack_knife_", 15).loadImages();
        knifeMove = new MultifileSpriteSheet("character/knife/move/survivor-move_knife_", 20).loadImages();

        flashlightIdle = new MultifileSpriteSheet("character/flashlight/idle/survivor-idle_flashlight_", 20).loadImages();
        flashlightMelee = new MultifileSpriteSheet("character/flashlight/meleeattack/survivor-meleeattack_flashlight_", 15).loadImages();
        flashlightMove = new MultifileSpriteSheet("character/flashlight/move/survivor-move_flashlight_", 20).loadImages();

        rifleIdle = new MultifileSpriteSheet("character/rifle/idle/survivor-idle_rifle_", 20).loadImages();
        rifleMelee = new MultifileSpriteSheet("character/rifle/meleeattack/survivor-meleeattack_rifle_", 15).loadImages();
        rifleMove = new MultifileSpriteSheet("character/rifle/move/survivor-move_rifle_", 20).loadImages();
        rifleReload = new MultifileSpriteSheet("character/rifle/reload/survivor-reload_rifle_", 20).loadImages();
        rifleShoot = new MultifileSpriteSheet("character/rifle/shoot/survivor-shoot_rifle_", 3).loadImages();

        shotgunIdle = new MultifileSpriteSheet("character/shotgun/idle/survivor-idle_shotgun_", 20).loadImages();
        shotgunMelee = new MultifileSpriteSheet("character/shotgun/meleeattack/survivor-meleeattack_shotgun_", 15).loadImages();
        shotgunMove = new MultifileSpriteSheet("character/shotgun/move/survivor-move_shotgun_", 20).loadImages();
        shotgunReload = new MultifileSpriteSheet("character/shotgun/reload/survivor-reload_shotgun_", 20).loadImages();
        shotgunShoot = new MultifileSpriteSheet("character/shotgun/shoot/survivor-shoot_shotgun_", 3).loadImages();
    }
}
