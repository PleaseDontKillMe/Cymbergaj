package danon.Cymbergaj.View.Renderer.Character;

import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.SpriteSheet;

public class RifleWeaponSheet implements CharacterWeaponSheet {
    private final ImagesRepository imagesRepository;

    public RifleWeaponSheet(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    public SpriteSheet getIdle() {
        return imagesRepository.rifleIdle;
    }

    @Override
    public SpriteSheet getMelee() {
        return imagesRepository.rifleMelee;
    }

    @Override
    public SpriteSheet getMove() {
        return imagesRepository.rifleMove;
    }

    @Override
    public SpriteSheet getReload() {
        return imagesRepository.rifleReload;
    }

    @Override
    public SpriteSheet getShoot() {
        return imagesRepository.rifleShoot;
    }
}
