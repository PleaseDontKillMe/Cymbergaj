package Application.Debug;

import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Model.World.Character.Fireball;
import Application.Model.World.DebugExportInterface;
import Application.View.Renderer.Renderer;

public class DebugExport implements DebugExportInterface {

    public Fireball fireball = new Fireball(new Point(), new Angle());
    public Double distance1, distance2, distance;
    public Double actualDistance;
    public Point a = new Point(), b = new Point(), ship = new Point();
    public Angle direction = new Angle();

    public Renderer getRenderer() {
        return new DebugRenderer(this);
    }

    @Override
    public void setFireball(Fireball fireball) {
        this.fireball = fireball;
    }

    @Override
    public void doStuffWith(Point a, Point ship, Angle direction, double distance) {
        this.a = a;
        this.b = new Point();
        this.ship = ship;
        this.direction = direction;
        actualDistance = distance;
    }

    @Override
    public void doubleDistance(double distance1, double distance2) {
        this.distance1 = distance1;
        this.distance2 = distance2;
    }

    @Override
    public void singleDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public void hasNoDistance() {
        distance1 = null;
        distance2 = null;
        distance = null;
        actualDistance = null;
    }

    @Override
    public void clear() {
        fireball = new Fireball(new Point(), new Angle());
        distance1 = null;
        distance2 = null;
        distance = null;
        actualDistance = null;
        a = new Point();
        b = new Point();
        ship = new Point();
        direction = new Angle();
    }
}
