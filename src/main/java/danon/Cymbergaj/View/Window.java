package danon.Cymbergaj.View;

import danon.Cymbergaj.JFrameFactory;
import danon.Cymbergaj.Model.GameEventListener;
import danon.Cymbergaj.Model.WindowClosingListener;
import danon.Cymbergaj.Settings;

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

    private final List<danon.Cymbergaj.View.Renderer.Renderer> renderers = new LinkedList<>();

    public Window(Settings settings, WindowClosingListener listener) {
        this.frame = JFrameFactory.create(settings, listener);

        backBuffer = new BufferedImage(settings.size.getWidth(), settings.size.getHeight(), BufferedImage.TYPE_INT_ARGB);
        canvas = backBuffer.createGraphics();
    }

    public void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
    }

    public void addRenderer(danon.Cymbergaj.View.Renderer.Renderer renderer) {
        renderers.add(renderer);
    }

    public void show() {
        frame.setVisible(true);
        windowGraphics = frame.getGraphics();
    }

    @Override
    public void render() {
        renderers.forEach(renderer -> renderer.renderOn(canvas));
        flip();
    }

    @Override
    public void update() {
        renderers.forEach(danon.Cymbergaj.View.Renderer.Renderer::update);
        renderers.removeIf(danon.Cymbergaj.View.Renderer.Renderer::isFinished);
    }

    private void flip() {
        windowGraphics.drawImage(backBuffer, 0, 0, null);
    }
}
