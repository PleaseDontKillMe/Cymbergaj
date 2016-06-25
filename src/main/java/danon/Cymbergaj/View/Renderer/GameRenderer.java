package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameRenderer implements Renderer {

    private final Game game;
    private final BufferedImage background;

    public GameRenderer(Game game, ImagesRepository images) {
        this.game = game;
        this.background = images.background2;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        int width = background.getWidth();
        int x = game.getBackgroundXSlide() % width;

        int halfHeight = game.getSize().getHeight() / 2;
        int halfWidth = game.getSize().getWidth() / 2;

        canvas.drawImage(background, -(x + halfWidth), -halfHeight, null);
        canvas.drawImage(background, -(x + halfWidth - width), -halfHeight, null);
        canvas.drawImage(background, -(x + halfWidth - width * 2), -halfHeight, null);
    }
}
