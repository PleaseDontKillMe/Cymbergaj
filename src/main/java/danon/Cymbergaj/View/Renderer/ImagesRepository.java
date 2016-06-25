package danon.Cymbergaj.View.Renderer;


import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.View.SpriteSheetDefinition;
import danon.Cymbergaj.View.SpriteSheetDefinitionBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesRepository {

    BufferedImage background;
    SpriteSheetDefinition fireball;
    SpriteSheetDefinition spaceship;

    public void load() {
        background = loadImage("space-background.png");

        fireball = sprites("fireball.png")
                .amount(8)
                .size(new Size(64, 64))
                .columns(8)
                .buildDefinition();

        spaceship = sprites("spaceship2.png")
                .amount(32)
                .size(new Size(64, 64))
                .buildDefinition();
    }

    private BufferedImage loadImage(String filename) {
        try {
            return ImageIO.read(new File("res/" + filename));
        } catch (IOException ignored) {
            throw new RuntimeException("File " + filename + " not found");
        }
    }

    private SpriteSheetDefinitionBuilder sprites(String filename) {
        return new SpriteSheetDefinitionBuilder().image(loadImage(filename));
    }
}
