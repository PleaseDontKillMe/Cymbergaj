package Application.View.Renderer;

import Application.Model.World.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WorldRenderer implements Renderer {
    private World world;
    private BufferedImage background;

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
    }
}
