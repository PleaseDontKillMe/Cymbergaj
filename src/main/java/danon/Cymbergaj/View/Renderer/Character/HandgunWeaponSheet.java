package danon.Cymbergaj.View.Renderer.Character;

import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.SpriteSheet;

public class HandgunWeaponSheet implements CharacterWeaponSheet {
    private final ImagesRepository imagesRepository;

    public HandgunWeaponSheet(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    public SpriteSheet getIdle() {
        return imagesRepository.handgunIdle;
    }

    @Override
    public SpriteSheet getMelee() {
        return imagesRepository.handgunMelee;
    }

    @Override
    public SpriteSheet getMove() {
        return imagesRepository.handgunMove;
    }

    @Override
    public SpriteSheet getReload() {
        return imagesRepository.handgunReload;
    }

    @Override
    public SpriteSheet getShoot() {
        return imagesRepository.handgunShoot;
    }
}
