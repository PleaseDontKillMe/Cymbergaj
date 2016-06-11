package Application.View;

import Application.Geometry.Size;
import Application.JFrameFactory;
import Application.Model.GameEventListener;
import Application.Model.WindowClosingListener;
import Application.Model.World.World;
import Application.Settings;

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
    private final Images images;
    private final World world;
    private Graphics windowGraphics;

    private List<Renderer> renderers = new LinkedList<>();

    public Window(Settings settings, Images images, WindowClosingListener listener, World world) {
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

    public void addRenderer(Renderer renderer) {
        renderers.add(renderer);
    }

    public void show() {
        frame.setVisible(true);
        windowGraphics = frame.getGraphics();
    }

    double frames = 0;

    @Override
    public void render() {
        int x = world.backgroundX() % 600;

        canvas.drawImage(images.background2, -(x), 0, null);
        canvas.drawImage(images.background2, -(x - 600), 0, null);
        canvas.drawImage(images.background2, -(x - 1200), 0, null);

        renderers.forEach(renderer -> renderer.renderOn(canvas));

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
