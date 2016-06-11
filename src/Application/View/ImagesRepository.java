package Application.View;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesRepository {

    BufferedImage plane;
    BufferedImage background;
    BufferedImage background2;

    public void load()
    {
        plane = loadImage("plane.png");
        background = loadImage("space-background1.png");
        background2 = loadImage("space-background2.png");
    }

    private BufferedImage loadImage(String filename)
    {
        try {
            return ImageIO.read(new File("res/" + filename));
        } catch (IOException ignored) {
            throw new RuntimeException("File " + filename + " not found");
        }
    }
}
