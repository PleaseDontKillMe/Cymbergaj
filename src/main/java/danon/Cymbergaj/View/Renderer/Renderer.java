package danon.Cymbergaj.View.Renderer;


import java.awt.*;

public interface Renderer {

    void renderOn(Graphics2D canvas);

    default void update(double elapsedTime) {

    }

    default boolean isFinished() {
        return false;
    }
}
