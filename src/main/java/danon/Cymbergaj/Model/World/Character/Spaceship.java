package danon.Cymbergaj.Model.World.Character;

import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Model.World.Control.Control;
import danon.Cymbergaj.Model.World.Control.ControlKeys;
import danon.Cymbergaj.Model.World.Control.SpaceshipControl;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;
import danon.Cymbergaj.View.Renderer.SpaceshipRenderer;


public class Spaceship extends Body {
    private final ControlKeys keys;
    private double traveledDistance = 0;

    public Spaceship(Point position, ControlKeys keys) {
        super(position);
        this.keys = keys;
    }

    public Control getControl() {
        return new SpaceshipControl(this, keys);
    }

    @Override
    public int getRadius() {
        return 32;
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new SpaceshipRenderer(this, images);
    }

    public double getTraveledDistance() {
        return traveledDistance;
    }

    public void incTraveledDistance(double velocity) {
        this.traveledDistance += velocity;
    }
}
