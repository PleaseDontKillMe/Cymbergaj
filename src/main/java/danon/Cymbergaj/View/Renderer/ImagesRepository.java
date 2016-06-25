package danon.Cymbergaj.View.Renderer;


import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.View.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesRepository {

    BufferedImage background;
    SpriteSheet fireball;
    SpriteSheet spaceship;

    public void load() {
        background = loadImage("space-background.png");
        fireball = new SpriteSheet(loadImage("fireball.png"), new Size(64, 64), 8);
        spaceship = new SpriteSheet(loadImage("spaceship2.png"), new Size(64, 64), 32);
    }

    private BufferedImage loadImage(String filename) {
        try {
            return ImageIO.read(getClass().getClassLoader().getResource(filename));
        } catch (IOException ignored) {
            throw new RuntimeException("File " + filename + " not found");
        }
    }
}
