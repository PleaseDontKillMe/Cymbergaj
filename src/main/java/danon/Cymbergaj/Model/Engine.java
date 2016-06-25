package danon.Cymbergaj.Model;


import danon.Cymbergaj.Debug.dyn.DebugApplication;
import danon.Cymbergaj.View.Renderer.Renderable;
import danon.Cymbergaj.View.Renderer.Updatable;

import java.util.ArrayList;
import java.util.List;

public class Engine implements GameEventListener {

    private final List<Updatable> updatables = new ArrayList<>();
    private final List<Renderable> renderables = new ArrayList<>();
    private boolean shouldStop = false;

    public void start() {
        double previous = System.nanoTime();

        while (!shouldStop) {
            double current = System.nanoTime();
            double elapsed = current - previous;
            previous = current;
            updateAll(elapsed / DebugApplication.NANO_TO_BASE);
            renderAll();
        }
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

    private void updateAll(double elapsedSeconds) {
        updatables.forEach(listener -> listener.updateMe(elapsedSeconds));
    }

    private void renderAll() {
        renderables.forEach(listener -> listener.renderOn(null));
    }
}
