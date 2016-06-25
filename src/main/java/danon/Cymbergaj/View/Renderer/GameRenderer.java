package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameRenderer implements Renderer {
    private final Game game;
    private final BufferedImage background;

    private final static BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{10.0f}, 0.0f);

    public GameRenderer(Game game, ImagesRepository images) {
        this.game = game;
        this.background = images.background2;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        int width = background.getWidth();
        int x = game.getBackgroundXSlide() % width;

        canvas.drawImage(background, -(x), 0, null);
        canvas.drawImage(background, -(x - width), 0, null);
        canvas.drawImage(background, -(x - width * 2), 0, null);

        canvas.setStroke(dashed);
    }
}