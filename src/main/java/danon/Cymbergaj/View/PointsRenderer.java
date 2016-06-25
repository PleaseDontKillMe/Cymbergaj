package danon.Cymbergaj.View;

import danon.Cymbergaj.Model.Game;
import danon.Cymbergaj.View.Renderer.Renderer;

import java.awt.*;

public class PointsRenderer implements Renderer {

    private final Game game;

    public PointsRenderer(Game game) {
        this.game = game;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        canvas.setColor(new Color(255, 255, 255, 60));
        drawPoint(canvas, game.getLeftPoints(), -240);
        drawSemicolon(canvas);
        drawPoint(canvas, game.getRightPoints(), 220);
    }

    private void drawSemicolon(Graphics2D canvas) {
        canvas.setFont(new Font("Arial", 0, 500));
        if (game.getLeftPoints() > 0 & game.getRightPoints() > 0) {
            canvas.drawString(":", -70, 160);
        }
    }

    private void drawPoint(Graphics2D canvas, int points, int x) {
        int fontSize, offsetX, offsetY;
        switch (String.valueOf(points).length()) {
            case 1:
                fontSize = 600;
                offsetY = 210;
                offsetX = 150;
                break;

            case 2:
                fontSize = 350;
                offsetY = 130;
                offsetX = 190;
                break;

            case 3:
            default:
                fontSize = 210;
                offsetY = 80;
                offsetX = 160;
                break;
        }

        if (points > 0) {
            canvas.setFont(new Font("Arial", 0, fontSize));
            canvas.drawString(String.valueOf(points), x - offsetX, offsetY );
        }
    }


}
