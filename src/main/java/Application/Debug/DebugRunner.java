package Application.Debug;

import Application.Geometry.Angle;
import Application.Geometry.Bounds;
import Application.Geometry.Point;
import Application.Geometry.Size;
import Application.Model.Engine;
import Application.Model.GameEventListener;
import Application.Model.WindowClosingListener;
import Application.Model.World.Character.Fireball;
import Application.Model.World.Character.Spaceship;
import Application.Model.World.Control.Control;
import Application.Model.World.Control.SpaceshipControl;
import Application.Model.World.Control.WsadControlKeys;
import Application.Model.World.World;
import Application.Settings;
import Application.View.Window;

public class DebugRunner implements GameEventListener {

    private final Engine engine = new Engine();
    private final Window window;

    private DebugRunner() {
        Settings settings = new Settings();
        settings.windowTitle = "Cymbergaj | Debug Mode";
        settings.size = new Size(1080, 620);

        DebugExport export = new DebugExport();

        World world = new World(new Bounds(new Point(100, 70), new Size(880, 500)), export);

        Spaceship leftShip = new DebugSpaceship(new Point(70, 200), new WsadControlKeys());
        Control leftControl = leftShip.getControl();

        Fireball fireball = new DebugFireball(world.getBounds().getCenter(), Angle.fromDegrees(40));
        Control fireballControl = fireball.getControl();

        world.addBody(leftShip);
        world.addBody(fireball);

        window = new Window(settings, new WindowClosingListener(engine));
        window.addKeyListener(leftControl);
        window.addKeyListener(fireballControl);
        window.addRenderer(new ClearScreenRenderer());
        window.addRenderer(export.getRenderer());
        window.addRenderer(leftShip.getRenderer(null));
        window.addRenderer(fireball.getRenderer(null));

        engine.addGameEventListener(window);
        engine.addGameEventListener(leftControl);
        engine.addGameEventListener(fireballControl);
        engine.addGameEventListener(world);
    }

    private void start() {
        window.show();
        engine.start();
    }

    public static void main(String[] args) {
        SpaceshipControl.VELOCITY = 1;
        Fireball.VELOCITY = 40;
        new DebugRunner().start();
    }
}
