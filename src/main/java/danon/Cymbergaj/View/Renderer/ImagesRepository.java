package danon.Cymbergaj.View.Renderer;


import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.View.PackedSpriteSheet;
import danon.Cymbergaj.View.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImagesRepository {

    BufferedImage background;
    SpriteSheet fireball;
    SpriteSheet spaceship;

    public void load() {
        background = loadImage("space-background.png");
        fireball = new PackedSpriteSheet(loadImage("fireball.png"), new Size(64, 64), 8);
        spaceship = new PackedSpriteSheet(loadImage("spaceship2.png"), new Size(64, 64), 32);
    }

    private BufferedImage loadImage(String filename) {
        URL resource = getClass().getClassLoader().getResource(filename);

        if (resource == null) {
            throw new RuntimeException("File " + filename + " not found");
        }

        try {
            return ImageIO.read(resource);
        } catch (IOException ignored) {
            throw new RuntimeException("File " + filename + " not found");
        }
    }
}
