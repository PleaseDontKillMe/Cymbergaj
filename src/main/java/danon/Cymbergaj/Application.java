package danon.Cymbergaj;

import danon.Cymbergaj.Geometry.Angle;
import danon.Cymbergaj.Geometry.Bounds;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.Engine;
import danon.Cymbergaj.Model.WindowClosingListener;
import danon.Cymbergaj.Model.World.Character.BigExplosion;
import danon.Cymbergaj.Model.World.Character.Explosion;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.Model.World.Control.ArrowsControlKeys;
import danon.Cymbergaj.Model.World.Control.Control;
import danon.Cymbergaj.Model.World.Control.WsadControlKeys;
import danon.Cymbergaj.Model.World.World;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Window;

class Application {

    private final Engine engine = new Engine();
    private final Window window;

    private Application() {
        Settings settings = new Settings();
        settings.windowTitle = "Cymbergaj | Best 2D game jk";
        settings.size = new Size(1080, 620);

        ImagesRepository images = new ImagesRepository();
        images.load();

        World world = new World();

        Spaceship leftShip = new Spaceship(new Point(70, 200), new WsadControlKeys());
        Control leftControl = leftShip.getControl();

        Spaceship rightShip = new Spaceship(new Point(settings.size.getWidth() - 122, 200), new ArrowsControlKeys());
        Control rightControl = rightShip.getControl();

        Explosion explosion = new BigExplosion(new Point(100, 300));
        Fireball fireball = new Fireball(new Point(500, 300), Angle.fromDegrees(40));
        Control fireballControl = fireball.getControl();

        window = new Window(settings, new WindowClosingListener(engine));
        window.addKeyListener(leftControl);
        window.addKeyListener(rightControl);
        window.addKeyListener(fireballControl);
        window.addRenderer(world.getRenderer(images));
        window.addRenderer(leftShip.getRenderer(images));
        window.addRenderer(rightShip.getRenderer(images));
        window.addRenderer(explosion.getRenderer(images));
        window.addRenderer(fireball.getRenderer(images));

        engine.addGameEventListener(window);
        engine.addGameEventListener(leftControl);
        engine.addGameEventListener(rightControl);
        engine.addGameEventListener(fireballControl);
        engine.addGameEventListener(world);
    }

    private void start() {
        window.show();
        engine.start();
    }

    public static void main(String[] args) {
        new Application().start();
    }
}
