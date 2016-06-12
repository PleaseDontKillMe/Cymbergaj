package Application;

import Application.Geometry.Angle;
import Application.Geometry.Bounds;
import Application.Geometry.Point;
import Application.Geometry.Size;
import Application.Model.Engine;
import Application.Model.WindowClosingListener;
import Application.Model.World.*;
import Application.Model.World.Character.BigExplosion;
import Application.Model.World.Character.Explosion;
import Application.Model.World.Character.Fireball;
import Application.Model.World.Character.Spaceship;
import Application.View.Renderer.ImagesRepository;
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

        World world = new World(new Bounds(new Point(100, 70), new Size(880, 500)));

        Spaceship leftShip = new Spaceship(new Point(50, 200), new WsadControlKeys());
        Control leftControl = leftShip.getControl();

        Spaceship rightShip = new Spaceship(new Point(settings.size.getWidth() - 114, 200), new ArrowsControlKeys());
        Control rightControl = rightShip.getControl();

        Explosion explosion = new BigExplosion(new Point(100, 300));
        Fireball fireball = new Fireball(world.getBounds().getCenter(), Angle.fromDegrees(40));
        Control fireballControl = fireball.getControl();

        world.addBody(leftShip);
        world.addBody(rightShip);
        world.addBody(fireball);

        window = new Window(settings, images, new WindowClosingListener(engine), world);
        window.addKeyListener(leftControl);
        window.addKeyListener(rightControl);
        window.addRenderer(world.getRenderer(images));
        window.addRenderer(leftShip.getRenderer(images));
        window.addRenderer(rightShip.getRenderer(images));
        window.addRenderer(explosion.getRenderer(images));
        window.addRenderer(fireball.getRenderer(images));

        engine.addGameEventListener(world);
        engine.addGameEventListener(window);
        engine.addGameEventListener(leftControl);
        engine.addGameEventListener(rightControl);
        engine.addGameEventListener(fireballControl);
    }

    private void start() {
        window.show();
        engine.start();
    }

    public static void main(String[] args) {
        new Application().start();
    }
}
