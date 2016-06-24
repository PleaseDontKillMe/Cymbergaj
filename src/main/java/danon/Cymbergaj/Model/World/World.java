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

    public Renderable getRenderer(ImagesRepository images) {
        return new WorldRenderer(this, images);
    }

    @Override
    public void update(double elapsedTime) {
        FireballBounce fireballBounce = new FireballBounce(bodies, export);
        InteruptiveShipMove interuptiveMove = new InteruptiveShipMove(bodies);
        backgroundSlide += backgroundSlideSpeed;
        bodies.forEach(body -> body.getControl().reactToBounds(bounds));
        bodies.stream()
                .filter(body -> body instanceof Fireball)
                .forEach(body -> fireballBounce.bounce((Fireball) body));
        bodies.stream()
                .filter(body -> body instanceof Spaceship)
                .forEach(body -> interuptiveMove.move((Spaceship)body));
    }
}