package danon.Cymbergaj.View.Renderer.Character;

import danon.Cymbergaj.View.SpriteSheet;

public interface CharacterWeaponSheet {
    SpriteSheet getIdle();

    SpriteSheet getMelee();

    SpriteSheet getMove();

    SpriteSheet getReload();

    SpriteSheet getShoot();
}
