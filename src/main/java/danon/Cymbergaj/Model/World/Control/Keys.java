package danon.Cymbergaj.Model.World.Control;

public class Keys {
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isLeft = false;
    private boolean isRight = false;

    public boolean isUp() {
        return isUp;
    }

    public boolean isDown() {
        return isDown;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public boolean playerMoves() {
        return (isUp != isDown) || (isLeft != isRight);
    }

    @Override
    public String toString() {
        return "Keys{" +
                "isUp=" + isUp +
                ", isDown=" + isDown +
                ", isLeft=" + isLeft +
                ", isRight=" + isRight +
                '}';
    }
}
