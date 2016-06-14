package Application.Model;


import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Model.World.Character.Body;
import Application.Model.World.Character.Fireball;
import Application.Model.World.FireballControl;

import java.util.List;

public class CircleBounce {
    private final Fireball bodyToBounce;
    private final List<Body> allBodies;

    public CircleBounce(Fireball bodyToBounce, List<Body> allBodies) {
        this.bodyToBounce = bodyToBounce;
        this.allBodies = allBodies;
    }

    public BounceResult bounce() {
        for (Body body : allBodies) {
            if (body == bodyToBounce) continue;
            if (bodiesOverlap(bodyToBounce, body) && bodiesHeadTowards(body)) {
                return bounceWithBodies(body);
            }
        }
        return new BounceResult(false);
    }

    private boolean bodiesOverlap(Body first, Body second) {
        double distance = first.getPosition().distanceTo(second.getPosition());
        return first.getRadius() + second.getRadius() >= distance;
    }

    private boolean bodiesHeadTowards(Body body) {
        Angle bodiesAngle = bodyToBounce.getPosition().angle(body.getPosition());
        return bodyToBounce.getDirection().between(bodiesAngle).getValue() <= Math.PI;
    }

    private BounceResult bounceWithBodies(Body body) {
        BounceResult result = new BounceResult(true, body);
        Angle angle = bodyToBounce.getPosition().angle(body.getPosition());
        FireballControl control = bodyToBounce.getControl();
        control.bounceAngle(angle);
        return result;
    }
}

class BounceResult {
    private boolean didBounce;
    private Body bouncedBody;
    private Point bouncePoint;

    public BounceResult(boolean didBounce) {
        this.didBounce = didBounce;
    }

    public BounceResult(boolean didBounce, Body bouncedBody) {
        this.didBounce = didBounce;
        this.bouncedBody = bouncedBody;
    }

    public void setBouncePoint(Point bouncePoint) {
        this.bouncePoint = bouncePoint;
    }

    public Point getBouncePoint() {
        return bouncePoint;
    }

    public Body getBouncedBody() {
        return bouncedBody;
    }

    public boolean didBounce() {
        return didBounce;
    }
}