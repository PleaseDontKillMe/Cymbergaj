package danon.Cymbergaj.Model.World.Character;


import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;
import org.dyn4j.dynamics.Body;

abstract public class GameObject extends Body {

    abstract public Renderer getRenderer(ImagesRepository images);
}
