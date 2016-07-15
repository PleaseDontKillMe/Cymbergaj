package danon.Cymbergaj.Model;


import java.util.ArrayList;
import java.util.List;

public class Engine {

    private final List<Updatable> onUpdate = new ArrayList<>();
    private final List<Runnable> onRender = new ArrayList<>();
    private volatile boolean shouldStop = false;

    public void start() {
        double previous = System.nanoTime();

        while (!shouldStop) {
            double current = System.nanoTime();
            double elapsed = current - previous;
            previous = current;
            updateAll(elapsed / 1.0e9);
            renderAll();
            try {
                Thread.sleep(2);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void stop() {
        this.shouldStop = true;
    }

    public void addUpdateListener(Updatable updatable) {
        this.onUpdate.add(updatable);
    }

    public void addRenderListener(Runnable runnable) {
        this.onRender.add(runnable);
    }

    private void updateAll(double elapsedSeconds) {
        onUpdate.forEach(updatable -> updatable.update(elapsedSeconds));
    }

    private void renderAll() {
        onRender.forEach(Runnable::run);
    }
}
