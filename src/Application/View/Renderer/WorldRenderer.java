package Application.View.Renderer;

import Application.Geometry.Size;
import Application.Model.World.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WorldRenderer implements Renderer {
    private final World world;
    private final BufferedImage background;

    final static float dash1[] = {10.0f};
    final static BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

    public WorldRenderer(World world, ImagesRepository images) {
        this.world = world;
        this.background = images.background2;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        int width = background.getWidth();
        int x = world.backgroundX() % width;

        canvas.drawImage(background, -(x), 0, null);
        canvas.drawImage(background, -(x - width), 0, null);
        canvas.drawImage(background, -(x - width*2), 0, null);

        Size size = world.getSize();
        canvas.setStroke(dashed);
        canvas.drawRect(0, 0, size.getWidth(), size.getHeight());
    }
}
