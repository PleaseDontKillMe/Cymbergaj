package danon.Cymbergaj.View.Renderer;


import java.awt.*;

public class ClearScreenRenderer implements Renderer {

    private final Dimension dimension;

    public ClearScreenRenderer(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        canvas.setColor(Color.WHITE);
        canvas.fillRect((int) (-dimension.getWidth() / 2), (int) (-dimension.getHeight() / 2), (int) dimension.getWidth(), (int) dimension.getHeight());
    }

}
