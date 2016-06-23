package danon.Cymbergaj.Debug.dyn;

import danon.Cymbergaj.Debug.ClearScreenRenderer;
import danon.Cymbergaj.Debug.DebugExport;
import danon.Cymbergaj.Debug.DebugFireball;
import danon.Cymbergaj.Debug.DebugSpaceship;
import danon.Cymbergaj.Geometry.Angle;
import danon.Cymbergaj.Geometry.Bounds;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.Engine;
import danon.Cymbergaj.Model.GameEventListener;
import danon.Cymbergaj.Model.WindowClosingListener;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.Model.World.Control.Control;
import danon.Cymbergaj.Model.World.Control.SpaceshipControl;
import danon.Cymbergaj.Model.World.Control.WsadControlKeys;
import danon.Cymbergaj.Model.World.World;
import danon.Cymbergaj.Settings;
import danon.Cymbergaj.View.Window;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

public class DynRunner implements GameEventListener {

    private final Window window;
    private final Engine engine = new Engine();

    private DynRunner() {
        Settings settings = new Settings();
        settings.windowTitle = "Cymbergaj | Debug Mode";
        settings.size = new Size(1080, 620);

        Engine engine = new Engine();
        window = new Window(settings, new WindowClosingListener(engine));
        engine.addGameEventListener(window);

        Rectangle rect = new Rectangle(100, 50);
        BodyFixture f = new BodyFixture(rect);
        f.setDensity(1.2);
        f.setFriction(0.6);

        Body b = new Body();
        b.addFixture(f);
        b.setLinearDamping(0.97);
       // b.setVelocity(new Vector2(0.0, 2.0));

        org.dyn4j.dynamics.World world = new org.dyn4j.dynamics.World();

        world.addBody(b);

        engine.addGameEventListener(new GameEventListener() {
            @Override
            public void update(double elapsedTime) {
               world.update(elapsedTime);
            }

            @Override
            public void render() {
                window.render();
              //  b.getP
            }
        });

        DebugExport export = new DebugExport();

        Spaceship leftShip = new DebugSpaceship(new Point(70, 200), new WsadControlKeys());
        Control leftControl = leftShip.getControl();

        Fireball fireball = new DebugFireball(new Point(200,300), Angle.fromDegrees(40));
        Control fireballControl = fireball.getControl();

        window.addKeyListener(leftControl);
        window.addKeyListener(fireballControl);
        window.addRenderer(new ClearScreenRenderer());
        window.addRenderer(export.getRenderer());
        window.addRenderer(leftShip.getRenderer(null));
        window.addRenderer(fireball.getRenderer(null));
    }

    private void start() {
        window.show();
        engine.start();
    }

    public static void main(String[] args) {
        SpaceshipControl.VELOCITY = 1;
        Fireball.VELOCITY = 40;
        new DynRunner().start();
    }
}
