package Application.View.Renderer;


import Application.Geometry.Size;
import Application.View.AnimatedSpriteSheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesRepository {

    BufferedImage plane;
    BufferedImage background;
    BufferedImage background2;
    AnimatedSpriteSheet fireball;
    AnimatedSpriteSheet spaceship;
    AnimatedSpriteSheet explosion;
    AnimatedSpriteSheet bigExplosion;

    public void load() {
        plane = image("plane.png");
        background = image("space-background1.png");
        background2 = image("space-background2.png");
        fireball = animatedImage("fireball.png", 8, new Size(64, 64), 8);
        spaceship = animatedImage("spaceship.png", 32, new Size(64, 64));
        explosion = animatedImage("explosion.png", 25, new Size(64, 64), 5);
        bigExplosion = animatedImage("big-explosion.png", 73, new Size(100,100), 9);
    }

    private BufferedImage image(String filename) {
        try {
            return ImageIO.read(new File("res/" + filename));
        } catch (IOException ignored) {
            throw new RuntimeException("File " + filename + " not found");
        }
    }

    private AnimatedSpriteSheet animatedImage(String filename, int spritesAmount, Size spriteSize) {
        return animatedImage(filename, spritesAmount, spriteSize, spritesAmount);
    }

    private AnimatedSpriteSheet animatedImage(String filename, int spritesAmount, Size spriteSize, int columnsCount) {
        BufferedImage image = image(filename);
        return new AnimatedSpriteSheet(image, spriteSize, spritesAmount, columnsCount);
    }
}
