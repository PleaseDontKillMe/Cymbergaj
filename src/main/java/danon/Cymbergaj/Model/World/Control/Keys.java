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

    void setUp(boolean up) {
        isUp = up;
    }

    void setDown(boolean down) {
        isDown = down;
    }

    void setLeft(boolean left) {
        isLeft = left;
    }

    void setRight(boolean right) {
        isRight = right;
    }

    public boolean playerMoves() {
        return (isUp != isDown) || (isLeft != isRight);
    }
}
