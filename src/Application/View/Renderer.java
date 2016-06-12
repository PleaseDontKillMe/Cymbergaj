package Application.View;


import java.awt.*;

public interface Renderer {
    void renderOn(Graphics2D canvas);
    boolean isFinished();
}
