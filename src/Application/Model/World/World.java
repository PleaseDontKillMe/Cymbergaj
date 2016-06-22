package Application.Model.World;


import Application.Geometry.Bounds;
import Application.Model.GameEventListener;
import Application.Model.World.Character.Body;
import Application.Model.World.Character.Fireball;
import Application.View.Renderer.ImagesRepository;
import Application.View.Renderer.Renderer;
import Application.View.Renderer.WorldRenderer;

import java.util.ArrayList;
import java.util.List;

public class World implements GameEventListener {

    private final List<Body> bodies = new ArrayList<>();
    private final Bounds bounds;
    private DebugExportInterface export = new IgnoreDebugExport();

    private double backgroundSlide = 0.0;
    private final static double backgroundSlideSpeed = 0.8;

    public World(Bounds bounds) {
        this.bounds = bounds;
    }

    public World(Bounds bounds, DebugExportInterface export) {
        this.bounds = bounds;
        this.export = export;
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

    public Renderer getRenderer(ImagesRepository images) {
        return new WorldRenderer(this, images);
    }

    @Override
    public void update() {
        FireballBounce fireballBounce = new FireballBounce(bodies, export);
        backgroundSlide += backgroundSlideSpeed;
        bodies.forEach(body -> body.getControl().reactToBounds(bounds));
        bodies.stream()
                .filter(body -> body instanceof Fireball)
                .forEach(body -> fireballBounce.bounce((Fireball) body));
    }
}