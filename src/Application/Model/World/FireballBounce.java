package Application.Model.World;

import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Model.Delta.QuadraticSolution;
import Application.Model.Delta.QuadraticSolver;
import Application.Model.World.Character.Body;
import Application.Model.World.Character.Fireball;

import java.util.List;

public class FireballBounce {
    private final Fireball bodyToBounce;
    private final List<Body> allBodies;

    public FireballBounce(Fireball body, List<Body> bodies) {
        this.bodyToBounce = body;
        this.allBodies = bodies;
    }

    public void bounce() {
        allBodies.stream()
                .filter(body -> body != bodyToBounce)
                .filter(this::bodiesHeadTowards)
                .forEach(this::bounceWithBody);
    }

    private boolean bodiesHeadTowards(Body second) {
        return true;
    }

    private void bounceWithBody(Body body) {
        Point a = bodyToBounce.getPosition().find(bodyToBounce.getRadius(), bodyToBounce.getDirection());
        Point c = body.getPosition();
        Angle direction = bodyToBounce.getDirection();

        double a1 = 1;
        double b1 = 2 * (direction.sin() * (a.y - c.y) + direction.cos() * (a.x - c.x));
        double c1 = a.distancePowTo(c) - Math.pow(body.getRadius(), 2);

        QuadraticSolution solution = new QuadraticSolver(a1, b1, c1).solve();

        if (solution.hasOne()) {
            double distance = solution.getFirst();
            if (0 < distance && distance <= bodyToBounce.getVelocity()) {
                doStuffWithDistance(a, c, direction, distance);
            }
            System.out.println(solution);
        }

        if (solution.hasTwo()) {
            double distance1 = solution.getFirst();
            double distance2 = solution.getSecond();
            double distance;
            if (distance1 > 0) {
                if (distance2 >= 0) {
                    distance = Math.min(distance1, distance2);
                }
                else {
                    distance = distance1;
                }
            }
            else {
                if (distance2 >= 0) {
                    distance = distance2;
                } else {
                    distance = -1;
                }
            }

            if (0 < distance && distance <= bodyToBounce.getVelocity()) {
                doStuffWithDistance(a, c, direction, distance);
            }
            print(distance);
        }
    }

    static double previous = 0;
    private void print(double distance) {
        if (distance - previous > 1) {
            System.out.println(distance);
            previous = distance;
        }
    }

    private void doStuffWithDistance(Point a, Point c, Angle direction, double distance) {
        double remain = bodyToBounce.getVelocity() - distance;
        Point b = a.find(distance, direction);
        bodyToBounce.getControl().bounceAngle(b.angleTo(c).plus(Math.PI/2));
        bodyToBounce.getPosition().setSize(b);
        bodyToBounce.getControl().moveAlong(remain);
    }


}
