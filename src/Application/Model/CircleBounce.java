package Application.Model;


import Application.Geometry.Point;
import Application.Model.World.Character.Body;

import java.util.List;

public class CircleBounce {
    private final Body bodyToBounce;
    private final List<Body> allBodies;

    public CircleBounce(Body bodyToBounce, List<Body> allBodies) {
        this.bodyToBounce = bodyToBounce;
        this.allBodies = allBodies;
    }

    public BounceResult bounce() {
        for (Body body : allBodies) {
            if (bodiesOverlap(bodyToBounce, body)) {
                return bounceBodies(bodyToBounce, body);
            }
        }
        return new BounceResult(false);
    }

    private boolean bodiesOverlap(Body first, Body second) {
        double distance = first.getPosition().distanceTo(second.getPosition());
        return first.getRadius() + second.getRadius() > distance;
    }

    private BounceResult bounceBodies(Body bodyToBounce, Body body) {

        BounceResult result = new BounceResult(true, body);

        bodyToBounce.getPosition().angle(body.getPosition());

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