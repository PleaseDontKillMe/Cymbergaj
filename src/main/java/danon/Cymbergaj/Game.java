package danon.Cymbergaj;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.View.Renderer.GameRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;
import danon.Cymbergaj.View.Renderer.Updatable;

public class Game implements Updatable {

    private int points1 = 0, points2 = 0;
    private int backgroundXSlide = 0;
    private final Size size;

    public Game(Size size) {
        this.size = size;
    }

    public void pointForLeft() {
        points1++;
    }

    public void pointForRight() {
        points2++;
    }

    public void restartGame() {
        points1 = 0;
        points2 = 0;
    }

    @Override
    public void updateMe(double elapsedTime) {
        backgroundXSlide -= elapsedTime*20;
    }

    public int getBackgroundXSlide() {
        return backgroundXSlide;
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new GameRenderer(this, images);
    }

    public Size getSize() {
        return size;
    }
}