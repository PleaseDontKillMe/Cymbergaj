package Application.View;

import Application.Geometry.Size;
import Application.JFrameFactory;
import Application.Model.GameEventListener;
import Application.Model.WindowClosingListener;
import Application.Model.World.World;
import Application.Settings;
import Application.View.Renderer.ImagesRepository;
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
    private final ImagesRepository images;
    private final World world;
    private Graphics windowGraphics;
    double frames = 0;

    private List<Application.View.Renderer.Renderer> renderers = new LinkedList<>();

    public Window(Settings settings, ImagesRepository images, WindowClosingListener listener, World world) {
        this.images = images;
        this.world = world;
        this.frame = JFrameFactory.create(settings, listener);

        Size size = settings.size;
        backBuffer = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_ARGB);
        canvas = backBuffer.createGraphics();
    }

    public void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
    }

    public void addRenderer(Application.View.Renderer.Renderer renderer) {
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

    @Override
    public void update() {
        frames -= 0.5;
    }

    private void flip() {
        windowGraphics.drawImage(backBuffer, 0, 0, null);
    }
}
