package Application.Model.World;

import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Model.World.Character.Fireball;

public class IgnoreDebugExport implements DebugExportInterface {

    @Override
    public void doStuffWith(Point a, Point ship, Angle direction, double distance) {
    }

    @Override
    public void doubleDistance(double distance1, double distance2) {
    }

    @Override
    public void singleDistance(double distance) {
    }

    @Override
    public void hasNoDistance() {
    }

    @Override
    public void setFireball(Fireball fireball) {
    }

    @Override
    public void clear() {
    }
}
