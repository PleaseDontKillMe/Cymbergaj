package danon.Cymbergaj.View.Renderer;


import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.View.Load;
import danon.Cymbergaj.View.MultifileSpriteSheet;
import danon.Cymbergaj.View.PackedSpriteSheet;
import danon.Cymbergaj.View.Renderer.Character.*;
import danon.Cymbergaj.View.SpriteSheet;
import danon.Cymbergaj.WeaponType;

import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

public class ImagesRepository {
    BufferedImage background;
    SpriteSheet fireball;
    SpriteSheet spaceship;

    public SpriteSheet feetIdle;
    public SpriteSheet feetRun;
    public SpriteSheet feetWalk;
    public SpriteSheet feetStrafeLeft;
    public SpriteSheet feetStrafeRight;

    public SpriteSheet handgunIdle;
    public SpriteSheet handgunMelee;
    public SpriteSheet handgunMove;
    public SpriteSheet handgunReload;
    public SpriteSheet handgunShoot;

    public SpriteSheet knifeIdle;
    public SpriteSheet knifeMelee;
    public SpriteSheet knifeMove;

    public SpriteSheet flashlightIdle;
    public SpriteSheet flashlightMelee;
    public SpriteSheet flashlightMove;

    public SpriteSheet rifleIdle;
    public SpriteSheet rifleMelee;
    public SpriteSheet rifleMove;
    public SpriteSheet rifleReload;
    public SpriteSheet rifleShoot;

    public SpriteSheet shotgunIdle;
    public SpriteSheet shotgunMelee;
    public SpriteSheet shotgunMove;
    public SpriteSheet shotgunReload;
    public SpriteSheet shotgunShoot;

    final Map<WeaponType, CharacterWeaponSheet> weaponSheets = new EnumMap<>(WeaponType.class);

    public void load() {
        background = Load.image("space-background.png");
        fireball = new PackedSpriteSheet(Load.image("fireball.png"), new Size(64, 64), 8);
        spaceship = new PackedSpriteSheet(Load.image("spaceship2.png"), new Size(64, 64), 32);

        feetIdle = new MultifileSpriteSheet("character/feet/idle/survivor-idle_", 1).loadImages();
        feetRun = new MultifileSpriteSheet("character/feet/run/survivor-run_", 20).loadImages();
        feetWalk = new MultifileSpriteSheet("character/feet/walk/survivor-walk_", 20, new Point(82, 73)).loadImages();
        feetStrafeLeft = new MultifileSpriteSheet("character/feet/strafe_left/survivor-strafe_left_", 20).loadImages();
        feetStrafeRight = new MultifileSpriteSheet("character/feet/strafe_right/survivor-strafe_right_", 20).loadImages();

        handgunIdle = new MultifileSpriteSheet("character/handgun/idle/survivor-idle_handgun_", 20).loadImages();
        handgunMelee = new MultifileSpriteSheet("character/handgun/meleeattack/survivor-meleeattack_handgun_", 15).loadImages();
        handgunMove = new MultifileSpriteSheet("character/handgun/move/survivor-move_handgun_", 20, new Point(121, 120)).loadImages();
        handgunReload = new MultifileSpriteSheet("character/handgun/reload/survivor-reload_handgun_", 15).loadImages();
        handgunShoot = new MultifileSpriteSheet("character/handgun/shoot/survivor-shoot_handgun_", 3).loadImages();

        knifeIdle = new MultifileSpriteSheet("character/knife/idle/survivor-idle_knife_", 20).loadImages();
        knifeMelee = new MultifileSpriteSheet("character/knife/meleeattack/survivor-meleeattack_knife_", 15).loadImages();
        knifeMove = new MultifileSpriteSheet("character/knife/move/survivor-move_knife_", 20).loadImages();

        flashlightIdle = new MultifileSpriteSheet("character/flashlight/idle/survivor-idle_flashlight_", 20).loadImages();
        flashlightMelee = new MultifileSpriteSheet("character/flashlight/meleeattack/survivor-meleeattack_flashlight_", 15, new Point(120, 142)).loadImages();
        flashlightMove = new MultifileSpriteSheet("character/flashlight/move/survivor-move_flashlight_", 20, new Point(116, 127)).loadImages();

        rifleIdle = new MultifileSpriteSheet("character/rifle/idle/survivor-idle_rifle_", 20).loadImages();
        rifleMelee = new MultifileSpriteSheet("character/rifle/meleeattack/survivor-meleeattack_rifle_", 15, new Point(136, 200)).loadImages();
        rifleMove = new MultifileSpriteSheet("character/rifle/move/survivor-move_rifle_", 20).loadImages();
        rifleReload = new MultifileSpriteSheet("character/rifle/reload/survivor-reload_rifle_", 20).loadImages();
        rifleShoot = new MultifileSpriteSheet("character/rifle/shoot/survivor-shoot_rifle_", 3).loadImages();

        shotgunIdle = new MultifileSpriteSheet("character/shotgun/idle/survivor-idle_shotgun_", 20, new Point(116, 120)).loadImages();
        shotgunMelee = new MultifileSpriteSheet("character/shotgun/meleeattack/survivor-meleeattack_shotgun_", 15, new Point(136, 200)).loadImages();
        shotgunMove = new MultifileSpriteSheet("character/shotgun/move/survivor-move_shotgun_", 20, new Point(116, 120)).loadImages();
        shotgunReload = new MultifileSpriteSheet("character/shotgun/reload/survivor-reload_shotgun_", 20, new Point(121, 120)).loadImages();
        shotgunShoot = new MultifileSpriteSheet("character/shotgun/shoot/survivor-shoot_shotgun_", 3).loadImages();

        weaponSheets.put(WeaponType.FlashLight, new FlashlightWeaponSheet(this));
        weaponSheets.put(WeaponType.Knife, new KnifeWeaponSheet(this));
        weaponSheets.put(WeaponType.HandGun, new HandgunWeaponSheet(this));
        weaponSheets.put(WeaponType.Rifle, new RifleWeaponSheet(this));
        weaponSheets.put(WeaponType.Shotgun, new ShotgunWeaponSheet(this));
    }
}
