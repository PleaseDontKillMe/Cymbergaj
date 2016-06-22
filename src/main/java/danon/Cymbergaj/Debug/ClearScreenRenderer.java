package danon.Cymbergaj.Debug;

import danon.Cymbergaj.View.Renderer.Renderer;

import java.awt.*;

public class ClearScreenRenderer implements Renderer {

    @Override
    public void renderOn(Graphics2D canvas) {
        canvas.setColor(Color.white);
        canvas.fillRect(0, 0, 1500, 800);
        canvas.setColor(Color.black);
    }
}
