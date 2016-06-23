package danon.Cymbergaj.Model;


import java.util.ArrayList;
import java.util.List;

public class Engine implements GameEventListener {

    private final static int TPS = 150;

    private final List<GameEventListener> listeners = new ArrayList<>();
    private boolean shouldStop = false;

    public void start() {
        variableTimeStepLoop();
    }

    public void addGameEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    private void variableTimeStepLoop() {
        double previous = System.nanoTime();
        double lag = 0.0;

        while (!shouldStop) {
            double current = System.nanoTime();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed / 1000000;

            while (lag >= (1000 / TPS)) {
                updateAll(elapsed/1000);
                lag -= (1000 / TPS);
            }
            double p = System.nanoTime();
            renderAll();
            double time = (System.nanoTime() - p) / 1000000;

            fpsAll(1000 / time);
        }
        System.out.println("Finishing...");
    }

    private void updateAll(double elapsedSeconds) {
        listeners.forEach(listener -> listener.update(elapsedSeconds));
    }

    private void renderAll() {
        listeners.forEach(GameEventListener::render);
    }

    private void fpsAll(double fps) {
        listeners.forEach(listener -> listener.fps(fps));
    }

    @Override
    public void gameEnd() {
        listeners.forEach(listener -> {
            if (this != listener) listener.gameEnd();
        });
        shouldStop = true;
    }
}
