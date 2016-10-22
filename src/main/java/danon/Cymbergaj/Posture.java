package danon.Cymbergaj;

import danon.Cymbergaj.View.Renderer.Character.CharacterWeaponSheet;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.SpriteSheet;

public enum Posture {
    Idle {
        @Override
        public SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getIdle();
        }
    },

    Melee {
        @Override
        public SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getMelee();
        }
    },

    Move {
        @Override
        public SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getMove();
        }

        @Override
        public boolean shouldFeetProgress() {
            return true;
        }
    },

    Reload {
        @Override
        public SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getReload();
        }
    },

    Shoot {
        @Override
        public SpriteSheet getSheetFor(CharacterWeaponSheet sheet) {
            return sheet.getShoot();
        }
    };

    public abstract SpriteSheet getSheetFor(CharacterWeaponSheet sheet);

    public SpriteSheet getSheetForFeet(ImagesRepository images) {
        return images.feetWalk;
    }

    public boolean shouldFeetProgress() {
        return false;
    }
}
