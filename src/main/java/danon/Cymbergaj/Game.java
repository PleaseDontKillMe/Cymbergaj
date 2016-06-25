package danon.Cymbergaj;

import danon.Cymbergaj.View.Renderer.Updatable;

public class Game implements Updatable {
    private int points1 = 0, points2 = 0;
    private int backgroundXSlide = 0;

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


}