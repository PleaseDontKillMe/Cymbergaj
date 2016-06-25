package danon.Cymbergaj.Model;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.View.Renderer.GameRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;
import danon.Cymbergaj.View.Renderer.Updatable;

public class Game implements Updatable {

    private int points1 = 0, points2 = 0;
    private PreviousWinner previousWinner = PreviousWinner.None;
    private double backgroundXSlide = 0;
    private final Size size;
    private Runnable onGameStart;

    public Game(Size size) {
        this.size = size;
    }

    public void onGameStart(Runnable onGameStart) {
        this.onGameStart = onGameStart;
    }

    public void pointForLeft() {
        points1++;
    }

    public void pointForRight() {
        points2++;
    }

    public void restartGame() {
        if (points1 == points2) {
            previousWinner = PreviousWinner.Tie;
        }
        else {
            if (points1 > points2) {
                previousWinner = PreviousWinner.WsadPlayer;
            } else {
                previousWinner = PreviousWinner.ArrowsPlayer;
            }
        }
        points1 = 0;
        points2 = 0;
        onGameStart.run();
    }

    @Override
    public void updateMe(double elapsedTime) {
        backgroundXSlide += elapsedTime*50.0;
    }

    public int getBackgroundXSlide() {
        return (int) backgroundXSlide;
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new GameRenderer(this, images);
    }

    public Size getSize() {
        return size;
    }

    public PreviousWinner getPreviousWinner() {
        return previousWinner;
    }

    public int getLeftPoints() {
        return points1;
    }

    public int getRightPoints() {
        return points2;
    }
}