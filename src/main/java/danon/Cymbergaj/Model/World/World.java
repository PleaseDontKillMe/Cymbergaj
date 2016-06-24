package danon.Cymbergaj.Model.World;


import danon.Cymbergaj.Geometry.Bounds;
import danon.Cymbergaj.Model.GameEventListener;
import danon.Cymbergaj.Model.World.Character.Body;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderable;
import danon.Cymbergaj.View.Renderer.WorldRenderer;

import java.util.ArrayList;
import java.util.List;

public class World implements GameEventListener {

    private final List<Body> bodies = new ArrayList<>();
    private final Bounds bounds;

    private double backgroundSlide = 0.0;
    private final static double backgroundSlideSpeed = 0.8;

    public World(Bounds bounds) {
        this.bounds = bounds;
    }

    public void addBody(Body body) {
        bodies.add(body);
    }

    public int backgroundX() {
        return (int) backgroundSlide;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public Renderable getRenderer(ImagesRepository images) {
        return new WorldRenderer(this, images);
    }

    @Override
    public void update(double elapsedTime) {
        backgroundSlide += backgroundSlideSpeed;
        bodies.forEach(body -> body.getControl().reactToBounds(bounds));
    }
}