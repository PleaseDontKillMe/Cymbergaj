package danon.Cymbergaj.Model;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.View.PointsRenderer;
import danon.Cymbergaj.View.Renderer.GameRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.Renderer;

public class Game implements Updatable {

    private int pointsLeft = 0, pointsRight = 0;
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

    void pointForLeft() {
        pointsLeft++;
    }

    void pointForRight() {
        pointsRight++;
    }

    public void restartGame() {
        if (pointsLeft == pointsRight) {
            previousWinner = PreviousWinner.Tie;
        }
        else {
            if (pointsLeft > pointsRight) {
                previousWinner = PreviousWinner.WsadPlayer;
            } else {
                previousWinner = PreviousWinner.ArrowsPlayer;
            }
        }
        pointsLeft = 0;
        pointsRight = 0;
        onGameStart.run();
    }

    @Override
    public void update(double elapsedTime) {
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
        return pointsLeft;
    }

    public int getRightPoints() {
        return pointsRight;
    }

    public PointsRenderer getPointsRenderer() {
        return new PointsRenderer(this);
    }
}