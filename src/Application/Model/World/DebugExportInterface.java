package Application.Model.World;

import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Model.World.Character.Fireball;

public interface DebugExportInterface {
    void doStuffWith(Point a, Point ship, Angle direction, double distance);
    void doubleDistance(double distance1, double distance2);
    void singleDistance(double distance);
    void hasNoDistance();
    void setFireball(Fireball fireball);
    void clear();
}
