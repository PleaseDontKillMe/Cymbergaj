package danon.Cymbergaj.Model.World.Control;

import danon.Chat.KeyMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SocketKeys extends Keys {

    private final ObjectOutputStream out;

    public SocketKeys(ObjectOutputStream out) {
        this.out = out;
    }

    @Override
    public void setUp(boolean up) {
        super.setUp(up);
        updateMove();
    }

    @Override
    public void setDown(boolean down) {
        super.setDown(down);
        updateMove();
    }

    @Override
    public void setLeft(boolean left) {
        super.setLeft(left);
        updateMove();
    }

    @Override
    public void setRight(boolean right) {
        super.setRight(right);
        updateMove();
    }

    private void updateMove() {
        try {
            out.writeObject(KeyMessage.fromKeys(this));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
