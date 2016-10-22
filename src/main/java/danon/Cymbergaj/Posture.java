package danon.Cymbergaj;

import danon.Cymbergaj.View.Renderer.Character.CharacterWeaponSheet;
import danon.Cymbergaj.View.SpriteSheet;

public enum Posture {
    Idle {
        @Override
        SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getIdle();
        }
    }, Melee {
        @Override
        SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getMelee();
        }
    }, Move {
        @Override
        SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getMove();
        }
    }, Reload {
        @Override
        SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getReload();
        }
    }, Shoot {
        @Override
        SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getShoot();
        }
    };

    abstract SpriteSheet getSheetFor(CharacterWeaponSheet sheet);
}
