package danon.Cymbergaj.View.Renderer;


import java.awt.*;

public interface Renderable {

    void renderOn(Graphics2D canvas);
    default void update() {}
    default boolean isFinished() {
        return false;
    }
}
