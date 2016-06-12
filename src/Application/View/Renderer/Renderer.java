package Application.View.Renderer;


import java.awt.*;

public interface Renderer {

    void renderOn(Graphics2D canvas);

    default boolean isFinished() {
        return false;
    }
}
