package danon.Cymbergaj.Model.World.Control;

import java.io.PrintWriter;

public class SocketKeys extends Keys {

    private final PrintWriter out;

    public SocketKeys(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void setUp(boolean up) {
        super.setUp(up);
        sendMove("UP___", up);
    }

    @Override
    public void setDown(boolean down) {
        super.setDown(down);
        sendMove("DOWN_", down);
    }

    @Override
    public void setLeft(boolean left) {
        super.setLeft(left);
        sendMove("LEFT_", left);
    }

    @Override
    public void setRight(boolean right) {
        super.setRight(right);
        sendMove("RIGHT", right);
    }

    private void sendMove(String direction, boolean state) {
        String charState = state ? "1" : "0";
        out.println("KEYS " + direction + "_" + charState);
    }
}
