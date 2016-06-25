package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.Model.GameObject;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;

public class Wall extends GameObject {

    @Override
    public Renderer getRenderer(ImagesRepository images) {
        return new BasicRenderer(this);
    }
}
