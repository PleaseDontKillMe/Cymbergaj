package danon.Cymbergaj.Model;


import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;
import org.dyn4j.dynamics.Body;

import java.awt.*;

abstract public class GameObject extends Body {
    private Color color;

    public GameObject() {
        this.color = new Color(
                (float) Math.random() * 0.5f + 0.5f,
                (float) Math.random() * 0.5f + 0.5f,
                (float) Math.random() * 0.5f + 0.5f);
    }

    abstract public Renderer getRenderer(ImagesRepository images);
}
