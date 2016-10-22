package danon.Cymbergaj.Model.World.Control;

public class Keys {
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isLeft = false;
    private boolean isRight = false;

    private boolean action1 = false;
    private boolean action2 = false;

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

    public boolean isAction1() {
        return action1;
    }

    public boolean isAction2() {
        return action2;
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

    public void setAction1(boolean action1) {
        this.action1 = action1;
    }

    public void setAction2(boolean action2) {
        this.action2 = action2;
    }

    @Override
    public String toString() {
        return (isUp ? "X" : "_") +
                (isDown ? "X" : "_") +
                (isLeft ? "X" : "_") +
                (isRight ? "X" : "_");
    }
}
