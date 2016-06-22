package Application.Model.World;

import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Model.Delta.QuadraticSolution;
import Application.Model.Delta.QuadraticSolver;
import Application.Model.World.Character.Body;
import Application.Model.World.Character.Fireball;

import java.util.List;

public class FireballBounce {
    private Fireball fireball;
    private final List<Body> allBodies;
    private final DebugExportInterface export;

    public FireballBounce(List<Body> bodies, DebugExportInterface export) {
        this.allBodies = bodies;
        this.export = export;
    }

    public FireballBounce(List<Body> bodies) {
        this.allBodies = bodies;
        this.export = new IgnoreDebugExport();
    }

    public void bounce(Fireball bodyToBounce) {
        export.clear();
        this.fireball = bodyToBounce;
        export.setFireball(fireball);
        allBodies.stream()
                .filter(body -> body != bodyToBounce)
                .filter(this::bodiesHeadTowards)
                .forEach(this::bounceWithBody);
    }

    private boolean bodiesHeadTowards(Body second) {
        return true;
    }

    private void bounceWithBody(Body body) {
        Point ship = body.getPosition();
        Point a = fireball.getPosition().find(fireball.getRadius(), fireball.getPosition().angleTo(ship));
        Angle direction = fireball.getDirection();

        double a1 = 1;
        double b1 = 2 * direction.sin() * (a.y - ship.y) + 2 * direction.cos() * (a.x - ship.x);
        double c1 = a.distancePowTo(ship) - Math.pow(body.getRadius(), 2);

        QuadraticSolution solution = new QuadraticSolver(a1, b1, c1).solve();

        if (solution.hasNone()) {
            export.hasNoDistance();
        }

        if (solution.hasOne()) {
            double distance = solution.getFirst();
            export.singleDistance(distance);
            if (0 < distance && distance <= fireball.getVelocity()) {
                doStuffWithDistance(a, ship, direction, distance);
            }
        }

        if (solution.hasTwo()) {
            double distance1 = solution.getFirst();
            double distance2 = solution.getSecond();
            export.doubleDistance(distance1, distance2);
            double distance;
            if (distance1 > 0) {
                if (distance2 >= 0) {
                    distance = Math.min(distance1, distance2);
                } else {
                    distance = distance1;
                }
            } else {
                if (distance2 >= 0) {
                    distance = distance2;
                } else {
                    distance = -1;
                }
            }

            if (0 < distance && distance <= fireball.getVelocity()*8) {
                doStuffWithDistance(a, ship, direction, distance);
            }
        }
    }

    private void doStuffWithDistance(Point a, Point ship, Angle direction, double distance) {
        export.doStuffWith(a, ship, direction, distance);
       /* double remain = fireball.getVelocity() - distance;
        Point b = a.find(distance, direction);
        fireball.getControl().bounceAngle(b.angleTo(ship).plus(Math.PI/2));
        fireball.getPosition().setSize(b);
        fireball.getControl().moveAlong(remain); */
    }
}