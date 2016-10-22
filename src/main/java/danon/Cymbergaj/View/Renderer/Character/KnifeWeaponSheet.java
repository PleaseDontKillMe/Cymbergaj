package danon.Cymbergaj.View.Renderer.Character;

import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.SpriteSheet;

public class KnifeWeaponSheet implements CharacterWeaponSheet {
    private final ImagesRepository imagesRepository;

    public KnifeWeaponSheet(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    public SpriteSheet getIdle() {
        return imagesRepository.knifeIdle;
    }

    @Override
    public SpriteSheet getMelee() {
        return imagesRepository.knifeMelee;
    }

    @Override
    public SpriteSheet getMove() {
        return imagesRepository.knifeMove;
    }

    @Override
    public SpriteSheet getReload() {
        return imagesRepository.knifeIdle;
    }

    @Override
    public SpriteSheet getShoot() {
        return imagesRepository.knifeMelee;
    }
}
