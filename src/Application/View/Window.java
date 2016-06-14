package Application.View;

import Application.JFrameFactory;
import Application.Model.GameEventListener;
import Application.Model.WindowClosingListener;
import Application.Settings;
import Application.View.Renderer.Renderer;

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

    private List<Application.View.Renderer.Renderer> renderers = new LinkedList<>();

    public Window(Settings settings, WindowClosingListener listener) {
        this.frame = JFrameFactory.create(settings, listener);

        backBuffer = new BufferedImage(settings.size.getWidth(), settings.size.getHeight(), BufferedImage.TYPE_INT_ARGB);
        canvas = backBuffer.createGraphics();
    }

    public void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
    }

    public void addRenderer(Renderer renderer) {
        renderers.add(renderer);
    }

    public void show() {
        frame.setVisible(true);
        windowGraphics = frame.getGraphics();
    }

    @Override
    public void render() {
        renderers.forEach(renderer -> renderer.renderOn(canvas));
        renderers.removeIf(Renderer::isFinished);

        flip();
    }

    private void flip() {
        windowGraphics.drawImage(backBuffer, 0, 0, null);
    }
}
