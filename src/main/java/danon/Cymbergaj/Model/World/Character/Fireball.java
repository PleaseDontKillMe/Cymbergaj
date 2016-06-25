package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.Model.GameObject;
import danon.Cymbergaj.View.Renderer.FireballRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderable;

import java.awt.*;

public class Fireball extends GameObject {

    @Override
    public void renderMe(Graphics2D canvas) {

    }

    public Renderable getRenderer(ImagesRepository images) {
        return new FireballRenderer(this, images);
    }
}
