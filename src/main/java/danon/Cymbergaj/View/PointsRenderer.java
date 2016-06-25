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
        canvas.drawString(game.getPoints1() + "", -360, 160);
        canvas.drawString(":", -70, 160);
        canvas.drawString(game.getPoints2() + "", 95, 160);
    }
}
