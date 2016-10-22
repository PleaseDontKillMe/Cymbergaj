package danon.Cymbergaj.View.Renderer.Character;

import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.SpriteSheet;

public class ShotgunWeaponSheet implements CharacterWeaponSheet {
    private final ImagesRepository imagesRepository;

    public ShotgunWeaponSheet(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    public SpriteSheet getIdle() {
        return imagesRepository.shotgunIdle;
    }

    @Override
    public SpriteSheet getMelee() {
        return imagesRepository.shotgunMelee;
    }

    @Override
    public SpriteSheet getMove() {
        return imagesRepository.shotgunMove;
    }

    @Override
    public SpriteSheet getReload() {
        return imagesRepository.shotgunReload;
    }

    @Override
    public SpriteSheet getShoot() {
        return imagesRepository.shotgunShoot;
    }
}
