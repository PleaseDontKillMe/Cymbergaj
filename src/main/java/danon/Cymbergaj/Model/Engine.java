package danon.Cymbergaj.Model;


import danon.Cymbergaj.View.Renderer.Renderable;
import danon.Cymbergaj.View.Renderer.Updatable;

import java.util.ArrayList;
import java.util.List;

public class Engine implements GameEventListener {

    private final static int TPS = 150;

    private final List<Updatable> updatables = new ArrayList<>();
    private final List<Renderable> renderables = new ArrayList<>();
    private boolean shouldStop = false;

    public void start() {
        fixedTimeStepLoop();
    }

    public void stop() {
        this.shouldStop = true;
    }

    public void addUpdatable(Updatable updatable) {
        this.updatables.add(updatable);
    }

    public void addRenderable(Renderable renderable) {
        this.renderables.add(renderable);
    }

    private void fixedTimeStepLoop() {
        double previous = System.nanoTime();
        double lag = 0.0;

        while (!shouldStop) {
            double current = System.nanoTime();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed / 1000000;

            while (lag >= (1000 / TPS)) {
                updateAll(elapsed/1000000000);
                lag -= (1000 / TPS);
            }
            renderAll();
        }
    }

    private void updateAll(double elapsedSeconds) {
        updatables.forEach(listener -> listener.updateMe(elapsedSeconds));
    }

    private void renderAll() {
        renderables.forEach(listener -> listener.renderOn(null));
    }
}
