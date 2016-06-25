package danon.Cymbergaj;

import danon.Cymbergaj.Debug.dyn.Spaceship;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.Engine;
import danon.Cymbergaj.Model.World.Character.BigExplosion;
import danon.Cymbergaj.Model.World.Character.Explosion;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.Model.World.Control.ArrowsControlKeys;
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

        Spaceship leftShip = new Spaceship(new WsadControlKeys());

        Spaceship rightShip = new Spaceship(new ArrowsControlKeys());

        Explosion explosion = new BigExplosion(new Point(100, 300));
        Fireball fireball = new Fireball();

        window = new Window(settings, e -> {
            engine.stop();
        });
        window.addKeyListener(leftShip);
        window.addKeyListener(rightShip);
        window.addRenderable(world.getRenderer(images));
        window.addRenderable(leftShip.getRenderer(images));
        window.addRenderable(rightShip.getRenderer(images));
        window.addRenderable(explosion.getRenderer(images));
        window.addRenderable(fireball.getRenderer(images));

       // engine.addGameEventListener(window);
        //engine.addGameEventListener(leftShip);
       // engine.addGameEventListener(rightShip);
        //engine.addGameEventListener(world);
    }

    private void start() {
        window.show();
        engine.start();
    }

    public static void main(String[] args) {
        new Application().start();
    }
}
