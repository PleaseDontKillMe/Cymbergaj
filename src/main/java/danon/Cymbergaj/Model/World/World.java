package danon.Cymbergaj.Model.World;


import danon.Cymbergaj.Model.GameEventListener;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderable;
import danon.Cymbergaj.View.Renderer.WorldRenderer;

public class World implements GameEventListener {
    private double backgroundSlide = 0.0;
    private final static double backgroundSlideSpeed = 0.8;

    public int backgroundX() {
        return (int) backgroundSlide;
    }

    public Renderable getRenderer(ImagesRepository images) {
        return new WorldRenderer(this, images);
    }

    @Override
    public void update(double elapsedTime) {
        backgroundSlide += backgroundSlideSpeed;
    }
}