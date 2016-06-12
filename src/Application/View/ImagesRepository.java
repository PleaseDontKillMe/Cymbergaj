package Application.View;


import Application.Geometry.Size;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesRepository {

    BufferedImage plane;
    BufferedImage background;
    BufferedImage background2;
    BufferedImage fireball;
    AnimatedSpriteSheet spaceship;
    AnimatedSpriteSheet explosion;

    public void load() {
        plane = loadImage("plane.png");
        background = loadImage("space-background1.png");
        background2 = loadImage("space-background2.png");
        fireball = loadImage("fireball.png");
        spaceship = loadAnimatedImage("spaceship.png", new Size(64, 64), 32);
        explosion = loadAnimatedImage("explosion.png", new Size(64, 64), 25, 5);
    }

    private BufferedImage loadImage(String filename) {
        try {
            return ImageIO.read(new File("res/" + filename));
        } catch (IOException ignored) {
            throw new RuntimeException("File " + filename + " not found");
        }
    }

    private AnimatedSpriteSheet loadAnimatedImage(String filename, Size spriteSize, int spritesAmount) {
        BufferedImage image = loadImage(filename);
        return new AnimatedSpriteSheet(image, spriteSize, spritesAmount);
    }

    private AnimatedSpriteSheet loadAnimatedImage(String filename, Size spriteSize, int spritesAmount, int columnsCount) {
        BufferedImage image = loadImage(filename);
        return new RowedAnimatedSpriteSheet(image, spriteSize, spritesAmount, columnsCount);
    }
}
