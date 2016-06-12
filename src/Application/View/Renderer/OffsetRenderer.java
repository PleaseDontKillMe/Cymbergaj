package Application.View.Renderer;

import Application.Geometry.Size;

import java.awt.*;

public class OffsetRenderer implements Renderer {

    private final Size offset;

    public OffsetRenderer(Size windowSize, Size arenaSize) {
        offset = new Size(
                (windowSize.getWidth() - arenaSize.getWidth()) / 2,
                (windowSize.getHeight() - arenaSize.getHeight()) / 2
        );
    }

    @Override
    public void renderOn(Graphics2D canvas) {
        canvas.translate(offset.getWidth(), offset.getHeight());
    }
}
