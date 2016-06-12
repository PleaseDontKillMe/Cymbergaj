package Application.Model.World;

import Application.Geometry.Bounds;
import Application.Model.GameEventListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface Control extends GameEventListener, KeyListener {

    void reactToBounds(Bounds bounds);

    default void keyTyped(KeyEvent e) {}
    default void keyPressed(KeyEvent e) {}
    default void keyReleased(KeyEvent e) {}
}
