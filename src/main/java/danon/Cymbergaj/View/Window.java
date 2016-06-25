package danon.Cymbergaj.View;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.JFrameFactory;
import danon.Cymbergaj.View.Renderer.Renderer;
import danon.Cymbergaj.WindowClosingListener;
import danon.Cymbergaj.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Window {

    private final JFrame frame;

    private final BufferedImage backBuffer;
    private final Graphics2D canvas;
    private Graphics windowGraphics;

    private final List<Renderer> renderers = new LinkedList<>();

    public Window(Settings settings, WindowClosingListener listener) {
        this.frame = JFrameFactory.create(settings, listener);
        Size size = settings.getSize();
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

        Dimension size = getDimension();
        AffineTransform move = AffineTransform.getTranslateInstance(size.getWidth()/2, size.getHeight()/2);
        canvas.transform(move);
    }

    public void render() {
        renderers.forEach(renderer -> renderer.renderOn(canvas));
        renderers.removeIf(Renderer::isFinished);
        flip();
    }

    private void flip() {
        windowGraphics.drawImage(backBuffer, 0, 0, null);
    }

    public Dimension getDimension() {
        return frame.getSize();
    }
}
