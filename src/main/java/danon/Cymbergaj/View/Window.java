package danon.Cymbergaj.View;

import danon.Cymbergaj.JFrameFactory;
import danon.Cymbergaj.Model.GameEventListener;
import danon.Cymbergaj.Model.WindowClosingListener;
import danon.Cymbergaj.Settings;
import danon.Cymbergaj.View.Renderer.Renderable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Window implements GameEventListener {

    private final JFrame frame;

    private final BufferedImage backBuffer;
    private final Graphics2D canvas;
    private Graphics windowGraphics;

    private final List<Renderable> renderables = new LinkedList<>();

    public Window(Settings settings, WindowClosingListener listener) {
        this.frame = JFrameFactory.create(settings, listener);

        backBuffer = new BufferedImage(settings.size.getWidth(), settings.size.getHeight(), BufferedImage.TYPE_INT_ARGB);
        canvas = backBuffer.createGraphics();
    }

    public void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
    }

    public void addRenderer(Renderable renderable) {
        renderables.add(renderable);
    }

    public void show() {
        frame.setVisible(true);
        windowGraphics = frame.getGraphics();
    }

    @Override
    public void render() {
        renderables.forEach(renderer -> renderer.renderOn(canvas));
        flip();
    }

    @Override
    public void update(double elapsedTime) {
        renderables.forEach(Renderable::update);
        renderables.removeIf(Renderable::isFinished);
    }

    private void flip() {
        windowGraphics.drawImage(backBuffer, 0, 0, null);
    }
}
