package danon.Cymbergaj;

import danon.Cymbergaj.View.Renderer.Character.CharacterWeaponSheet;
import danon.Cymbergaj.View.SpriteSheet;

public enum Posture {
    Idle {
        @Override
        public SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getIdle();
        }
    }, Melee {
        @Override
        public SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getMelee();
        }
    }, Move {
        @Override
        public SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getMove();
        }
    }, Reload {
        @Override
        public SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getReload();
        }
    }, Shoot {
        @Override
        public SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getShoot();
        }
    };

    public abstract SpriteSheet getSheetFor(CharacterWeaponSheet sheet);
}
