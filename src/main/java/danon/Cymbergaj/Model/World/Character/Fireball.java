package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.Model.GameObject;
import danon.Cymbergaj.View.Renderer.FireballRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;

public class Fireball extends GameObject  {

    public FireballRenderer getRenderer(ImagesRepository images) {
        return new FireballRenderer(this, images);
    }
}
