package Application.Model.World;


import Application.Geometry.Point;
import Application.Model.GameEventListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SpaceshipControl implements GameEventListener, Control, KeyListener {

    private final Spaceship spaceship;

    private boolean goDown = false, goUp = false;
    boolean alreadyShot;

    public SpaceshipControl(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public void update() {
        Point position = spaceship.getPosition();
        if (goDown) position.y += 5;
        if (goUp) position.y -= 5;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                goUp = true;
                break;
            case 's':
                goDown = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                goUp = false;
                break;
            case 's':
                goDown = false;
                break;
        }
    }
}
