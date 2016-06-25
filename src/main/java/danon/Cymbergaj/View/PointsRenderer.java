package danon.Cymbergaj.View;

import danon.Cymbergaj.Game;
import danon.Cymbergaj.View.Renderer.Renderer;

import java.awt.*;

public class PointsRenderer implements Renderer {

    private final Game game;

    public PointsRenderer(Game game) {
        this.game = game;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        canvas.setFont(new Font("Arial", 0, 500));
        canvas.setColor(new Color(255, 255, 255, 60));
        drawPoint(canvas, game.getPoints1(), -360);
        drawSemicolon(canvas);
        drawPoint(canvas, game.getPoints2(), 95);
    }

    private void drawSemicolon(Graphics2D canvas) {
        if (game.getPoints1() > 0 & game.getPoints2() > 0) {
            canvas.drawString(":", -70, 160);
        }
    }

    private void drawPoint(Graphics2D canvas, int points, int x) {
        if (points > 0) {
            canvas.drawString(String.valueOf(points), x, 160);
        }
    }


}
