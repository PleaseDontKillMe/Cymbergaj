package danon.Cymbergaj.Model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowClosingListener extends WindowAdapter {

    private final GameEventListener listener;

    public WindowClosingListener(GameEventListener listener) {
        this.listener = listener;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        listener.gameEnd();
    }
}