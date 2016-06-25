package danon.Cymbergaj.Model.World.Control;

class Keys {
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isLeft = false;
    private boolean isRight = false;

    boolean isUp() {
        return isUp;
    }

    boolean isDown() {
        return isDown;
    }

    boolean isLeft() {
        return isLeft;
    }

    boolean isRight() {
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

    boolean playerMoves() {
        return (isUp != isDown) || (isLeft != isRight);
    }
}
