package danon.Cymbergaj.Model;

import com.google.common.collect.ImmutableList;

import static java.lang.Thread.sleep;

public class EngineExecutor {
    private final ImmutableList<Updatable> onUpdate;
    private final ImmutableList<Runnable> onRender;
    private volatile boolean shouldStop = false;

    EngineExecutor(ImmutableList<Updatable> onUpdate, ImmutableList<Runnable> onRender) {
        this.onUpdate = onUpdate;
        this.onRender = onRender;
    }

    public void start() {
        if (shouldStop) {
            throw new RuntimeException("EngineExecutor restarted");
        }

        double previous = System.nanoTime();

        while (!shouldStop) {
            double current = System.nanoTime();
            double elapsed = current - previous;
            previous = current;
            updateAll(elapsed / 1.0e9);
            renderAll();
            try {
                sleep(2);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void stop() {
        shouldStop = true;
    }

    private void updateAll(double elapsedSeconds) {
        onUpdate.forEach(updatable -> updatable.update(elapsedSeconds));
    }

    private void renderAll() {
        onRender.forEach(Runnable::run);
    }
}