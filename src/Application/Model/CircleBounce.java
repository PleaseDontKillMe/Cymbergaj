package Application.Model;


import Application.Geometry.Angle;
import Application.Model.World.Character.Body;
import Application.Model.World.Character.Fireball;

import java.util.List;

public class CircleBounce {
    private final Fireball bodyToBounce;
    private final List<Body> allBodies;

    public CircleBounce(Fireball bodyToBounce, List<Body> allBodies) {
        this.bodyToBounce = bodyToBounce;
        this.allBodies = allBodies;
    }

    public void bounce() {
        allBodies.stream()
                .filter(body -> body != bodyToBounce)
                .filter(this::bodiesOverlap)
                .filter(this::bodiesHeadTowards)
                .forEach(this::bounceWithBody);
    }

    private boolean bodiesOverlap(Body second) {
        double distance = bodyToBounce.getPosition().distanceTo(second.getPosition());
        return distance <= bodyToBounce.getRadius() + second.getRadius();
    }

    private boolean bodiesHeadTowards(Body body) {
        Angle bodiesAngle = bodyToBounce.getPosition().angleTo(body.getPosition());
        return bodyToBounce.getDirection().between(bodiesAngle).getValue() <= Math.PI / 2;
    }

    private void bounceWithBody(Body body) {
        Angle angle = bodyToBounce.getPosition().angleTo(body.getPosition());
        bodyToBounce.getControl().bounceAngle(angle.plus(Math.PI / 2));
    }
}