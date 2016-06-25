package danon.Cymbergaj.Debug.dyn;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.Engine;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.Model.World.Control.ArrowsControlKeys;
import danon.Cymbergaj.Model.World.Control.WsadControlKeys;
import danon.Cymbergaj.Settings;
import danon.Cymbergaj.View.Window;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;

import java.awt.*;
import java.awt.geom.AffineTransform;


public final class DebugApplication {

    static final double SCALE = 45.0; //  The scale 45 pixels per meter
    private static final double NANO_TO_BASE = 1.0e9;

    private final Engine engine = new Engine();
    private final Window window;
    private World world;

    private DebugApplication() {
        Settings settings = new Settings();
        settings.windowTitle = "Cymbergaj | Best 2D game jk";
        settings.size = new Size(1080, 620);
        window = new Window(settings, event -> engine.stop());

        initializeWorld();
    }

    protected void initializeWorld() {
        this.world = new World();
        world.setGravity(World.ZERO_GRAVITY);

        // create the floor
        GameObject floor1 = new Wall(), floor2 = new Wall();

        Rectangle horizontalRectangle = new Rectangle(20.0, 0.2);
        floor1.addFixture(new BodyFixture(horizontalRectangle));
        floor1.setMass(MassType.INFINITE);
        floor2.addFixture(new BodyFixture(horizontalRectangle));
        floor2.setMass(MassType.INFINITE);

        floor1.translate(0.0, -7.0);
        floor2.translate(0.0, 7.0);
        this.world.addBody(floor1);
        this.world.addBody(floor2);

        GameObject wall1 = new Wall(), wall2 = new Wall();

        BodyFixture fixture = new BodyFixture(new Rectangle(0.2, 14.0));
        wall1.addFixture(fixture);
        wall1.setMass(MassType.INFINITE);
        wall2.addFixture(fixture);
        wall2.setMass(MassType.INFINITE);

        wall1.translate(-10.0, 0);
        wall2.translate(10.0, 0.0);
        this.world.addBody(wall1);
        this.world.addBody(wall2);

        // ball
        GameObject ball = new Fireball();
        BodyFixture ballFixture = new BodyFixture(new Circle(0.3));
        ballFixture.setRestitution(1.0);
        ball.addFixture(ballFixture);
        ball.setMass(MassType.NORMAL);
        ball.applyForce(new Vector2(-150.0, 0.0));
        ball.setLinearDamping(0.05);
        this.world.addBody(ball);

        // players
        Spaceship player1 = new Spaceship(new WsadControlKeys()), player2 = new Spaceship(new ArrowsControlKeys());
        player1.addFixture(new Circle(0.7));
        player1.setMass(MassType.FIXED_LINEAR_VELOCITY);
        player1.translate(-9.0, 0.0);
        player2.addFixture(new Circle(0.7));
        player2.translate(9.0, 0.0);
        player2.setMass(MassType.FIXED_LINEAR_VELOCITY);
        this.world.addBody(player1);
        this.world.addBody(player2);

        window.addKeyListener(player1);
        window.addKeyListener(player2);

        window.addRenderable(this::render);
    }

    public void start() {
        engine.addUpdatable(elapsedTime -> world.update(elapsedTime));
        engine.addRenderable(canvas1 -> window.render());

        window.show();
        engine.start();
    }

    protected void render(Graphics2D canvas) {
        canvas.setColor(Color.WHITE);
        Dimension size = window.getDimension();
        canvas.fillRect((int)(-size.getWidth()/2), (int) (-size.getHeight()/2), (int)size.getWidth(), (int)size.getHeight());

        for (int i = 0; i < this.world.getBodyCount(); i++) {
            GameObject go = (GameObject) this.world.getBody(i);
            go.render(canvas);
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        new DebugApplication().start();
    }
}