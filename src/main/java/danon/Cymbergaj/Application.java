package danon.Cymbergaj;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.Engine;
import danon.Cymbergaj.Model.GameObject;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.Model.World.Character.Wall;
import danon.Cymbergaj.Model.World.Control.ArrowsControlKeys;
import danon.Cymbergaj.Model.World.Control.Spaceship;
import danon.Cymbergaj.Model.World.Control.WsadControlKeys;
import danon.Cymbergaj.View.Window;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;

import java.awt.*;
import java.util.Objects;


public final class Application {

    public static final double SCALE = 45.0; //  The scale 45 pixels per meter
    public static final double NANO_TO_BASE = 1.0e9;

    private final Engine engine = new Engine();
    private final Window window;
    private final World world = new World();

    private int points1 = 0, points2 = 0;

    private Application() {
        Settings settings = new Settings();
        settings.windowTitle = "Cymbergaj | Best 2D game jk";
        settings.size = new Size(1080, 720);
        window = new Window(settings, event -> engine.stop());

        initializeWorld();
    }

    protected void initializeWorld() {
        world.setGravity(World.ZERO_GRAVITY);

        // create the floor
        GameObject floor1 = new Wall(), floor2 = new Wall();

        BodyFixture floorFixture = new BodyFixture(new Rectangle(20.0, 0.2));
        floorFixture.setRestitution(0.0);
        floor1.addFixture(floorFixture);
        floor2.addFixture(floorFixture);
        floor1.setMass(MassType.INFINITE);
        floor2.setMass(MassType.INFINITE);

        floor1.translate(0.0, -7.0);
        floor2.translate(0.0, 7.0);
        this.world.addBody(floor1);
        this.world.addBody(floor2);


        GameObject stopper1 = new Wall(), stopper2 = new Wall(), stopper3 = new Wall(), stopper4 = new Wall();
        BodyFixture stopperFixture = new BodyFixture(new Rectangle(0.6, 4.5));
        stopperFixture.setRestitution(0.0);
        stopper1.addFixture(stopperFixture);
        stopper2.addFixture(stopperFixture);
        stopper3.addFixture(stopperFixture);
        stopper4.addFixture(stopperFixture);
        stopper1.setMass(MassType.INFINITE);
        stopper2.setMass(MassType.INFINITE);
        stopper3.setMass(MassType.INFINITE);
        stopper4.setMass(MassType.INFINITE);

        double v = 9.7;
        double v1 = 4.76;
        stopper1.translate(-v, -v1);
        stopper2.translate(v, -v1);
        stopper3.translate(-v, v1);
        stopper4.translate(v, v1);
        this.world.addBody(stopper1);
        this.world.addBody(stopper2);
        this.world.addBody(stopper3);
        this.world.addBody(stopper4);


        GameObject wall1 = new Wall(), wall2 = new Wall();
        BodyFixture wallFixture = new BodyFixture(new Rectangle(0.2, 14.0));
        wallFixture.setRestitution(0.0);
        wall1.addFixture(wallFixture);
        wall1.setMass(MassType.INFINITE);
        wall2.addFixture(wallFixture);
        wall2.setMass(MassType.INFINITE);
        wall1.setUserData("left");
        wall2.setUserData("right");

        wall1.translate(-10.0, 0);
        wall2.translate(10.0, 0.0);
        this.world.addBody(wall1);
        this.world.addBody(wall2);

        world.addListener(new ContactAdapter() {
            @Override
            public void end(ContactPoint point) {
                String user1 = (String) point.getBody1().getUserData();
                String user2 = (String) point.getBody2().getUserData();
                if (user1 != null && user2 != null) {
                    if (user1.equals("ball") || user2.equals("ball")) {
                        if (Objects.equals(user1, "left") || Objects.equals(user2, "left")) {
                            points1++;
                        }
                        if (Objects.equals(user1, "right") || Objects.equals(user2, "right")) {
                            points2++;
                        }
                    }
                }
                super.end(point);
            }
        });

        // ball
        GameObject ball = new Fireball();
        BodyFixture ballFixture = new BodyFixture(new Circle(0.3));
        ballFixture.setRestitution(1.0);
        ball.addFixture(ballFixture);
        ball.setMass(MassType.NORMAL);
        ball.applyForce(new Vector2(-150.0, 0.0));
        ball.setLinearDamping(0.05);
        ball.setUserData("ball");
        this.world.addBody(ball);

        // players
        Spaceship player1 = new Spaceship(new WsadControlKeys()), player2 = new Spaceship(new ArrowsControlKeys());
        BodyFixture fixture = new BodyFixture(new Circle(0.7));
        fixture.setFriction(0.0);
        player1.addFixture(fixture);
        player1.setMass(MassType.NORMAL);
        player1.translate(-9.0, 0.0);
        player2.addFixture(fixture);
        player2.translate(9.0, 0.0);
        player2.setMass(MassType.NORMAL);

        this.world.addBody(player1);
        this.world.addBody(player2);

        player1.setAsleep(false);
        player2.setAsleep(false);

        window.addKeyListener(player1);
        window.addKeyListener(player2);

        window.addRenderable(this::render);
        window.addRenderable(canvas -> {
            canvas.setColor(Color.black);
            canvas.setFont(new Font("Arial", 0, 15));
            canvas.drawString(points1 + ":" + points2, 20, 50);
        });

        engine.addUpdatable(player1);
        engine.addUpdatable(player2);
    }

    public void start() {
        engine.addUpdatable(world::update);
        engine.addRenderable(canvas1 -> window.render());

        window.show();
        engine.start();
    }

    protected void render(Graphics2D canvas) {
        canvas.setColor(Color.WHITE);
        Dimension size = window.getDimension();
        canvas.fillRect((int) (-size.getWidth() / 2), (int) (-size.getHeight() / 2), (int) size.getWidth(), (int) size.getHeight());

        for (int i = 0; i < this.world.getBodyCount(); i++) {
            GameObject gameObject = (GameObject) this.world.getBody(i);
            gameObject.render(canvas);
        }
    }

    public static void main(String[] args) {
        new Application().start();
    }
}