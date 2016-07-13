package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Model.Game;
import danon.Cymbergaj.Model.PreviousWinner;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameRenderer implements Renderer {

    private final Game game;
    private final BufferedImage background;

    public GameRenderer(Game game, ImagesRepository images) {
        this.game = game;
        this.background = images.background;
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

        canvas.setColor(new Color(255,255,255, 120));
        String text = getEndGameText();
        canvas.setFont(new Font("Arial", 0, 80));
        canvas.drawString(text, -400, -200);
    }

    private String getEndGameText() {
        PreviousWinner previousWinner = game.getPreviousWinner();
        switch (previousWinner) {
            case WsadPlayer:
                return "WSAD Player won! :)";
            case ArrowsPlayer:
                return "Arrows Player won! :)";
            case Tie:
                return "Tie";
            case None:
            default:
                return "";
        }
    }
}
