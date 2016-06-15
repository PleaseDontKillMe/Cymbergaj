package Application.View.Renderer;


import java.awt.*;

public interface Renderer {

    void renderOn(Graphics2D canvas);
    default void update() {}
    default boolean isFinished() {
        return false;
    }
}
