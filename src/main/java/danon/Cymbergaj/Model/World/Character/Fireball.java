package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.Model.GameObject;
import danon.Cymbergaj.View.Renderer.FireballRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;

public class Fireball extends GameObject {

    public Renderer getRenderer(ImagesRepository images) {
        return new FireballRenderer(this, images);
    }
}
