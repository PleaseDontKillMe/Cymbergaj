package danon.Cymbergaj.View.Renderer.Character;

import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.SpriteSheet;

public class FlashlightWeaponSheet implements CharacterWeaponSheet {
    private final ImagesRepository imagesRepository;

    public FlashlightWeaponSheet(ImagesRepository imagesRepository) {

        this.imagesRepository = imagesRepository;
    }

    @Override
    public SpriteSheet getIdle() {
        return imagesRepository.flashlightIdle;
    }

    @Override
    public SpriteSheet getMelee() {
        return imagesRepository.flashlightMelee;
    }

    @Override
    public SpriteSheet getMove() {
        return imagesRepository.flashlightMove;
    }

    @Override
    public SpriteSheet getReload() {
        return imagesRepository.flashlightIdle;
    }

    @Override
    public SpriteSheet getShoot() {
        return imagesRepository.flashlightMelee;
    }
}
