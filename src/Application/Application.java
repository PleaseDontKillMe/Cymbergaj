package Application;

import Application.Geometry.Point;
import Application.Geometry.Size;
import Application.Model.Engine;
import Application.Model.WindowClosingListener;
import Application.Model.World.*;
import Application.View.ImagesRepository;
import Application.View.Window;

public class Application {

    private final Engine engine = new Engine();
    private final Window window;

    private Application() {
        Settings settings = new Settings();
        settings.windowTitle = "Cymbergaj | Best 2D game jk";
        settings.size = new Size(1080, 620);

        ImagesRepository images = new ImagesRepository();
        images.load();

        World world = new World();

        Spaceship ship = new Spaceship(new Point(50, 200));
        Control control = ship.getControl();

        Explosion explosion = new BigExplosion(new Point(100, 300));

        window = new Window(settings, images, new WindowClosingListener(engine), world);
        window.addKeyListener(control);
        window.addRenderer(ship.getRenderer(images));
        window.addRenderer(explosion.getRenderer(images));

        engine.addGameEventListener(world);
        engine.addGameEventListener(window);
        engine.addGameEventListener(control);
    }

    private void start() {
        window.show();
        engine.start();
    }

    public static void main(String[] args) {
        new Application().start();
    }
}
