package Application.View.Renderer;


import Application.Geometry.Size;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesRepository {

    BufferedImage plane;
    BufferedImage background;
    BufferedImage background2;
    SpriteSheetDefinition fireball;
    SpriteSheetDefinition spaceship;
    SpriteSheetDefinition explosion;
    SpriteSheetDefinition bigExplosion;

    public void load() {
        plane = loadImage("plane.png");
        background = loadImage("space-background1.png");
        background2 = loadImage("space-background2.png");

        fireball = image("fireball.png")
                .amount(8)
                .size(new Size(64, 64))
                .columns(8)
                .buildDefinition();

        spaceship = image("spaceship2.png")
                .amount(32)
                .size(new Size(64, 64))
                .buildDefinition();

        explosion = image("explosion.png")
                .amount(25)
                .size(new Size(64, 64))
                .columns(5)
                .buildDefinition();

        bigExplosion = image("big-explosion.png")
                .amount(73)
                .size(new Size(100, 100))
                .columns(9)
                .buildDefinition();
    }

    private BufferedImage loadImage(String filename) {
        try {
            return ImageIO.read(new File("res/" + filename));
        } catch (IOException ignored) {
            throw new RuntimeException("File " + filename + " not found");
        }
    }

    private SpriteSheetDefinitionBuilder image(String filename) {
        return new SpriteSheetDefinitionBuilder().image(loadImage(filename));
    }
}
