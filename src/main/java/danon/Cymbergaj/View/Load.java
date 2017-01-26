package danon.Cymbergaj.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Load {
    public static BufferedImage image(final String filename) {
        URL resource = Load.class.getClassLoader().getResource(filename);

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
