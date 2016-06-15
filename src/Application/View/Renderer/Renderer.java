package Application.View.Renderer;


import java.awt.*;

public interface Renderer {

    void renderOn(Graphics2D canvas);
    void update();

    default boolean isFinished() {
        return false;
    }
}
