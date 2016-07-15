package danon.Cymbergaj.Model;


import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Engine {
    private final List<Updatable> onUpdate = new ArrayList<>();
    private final List<Runnable> onRender = new ArrayList<>();

    public void addUpdateListener(Updatable updatable) {
        this.onUpdate.add(updatable);
    }

    public void addRenderListener(Runnable runnable) {
        this.onRender.add(runnable);
    }

    public EngineExecutor createExecutor() {
        return new EngineExecutor(ImmutableList.copyOf(onUpdate), ImmutableList.copyOf(onRender));
    }
}
