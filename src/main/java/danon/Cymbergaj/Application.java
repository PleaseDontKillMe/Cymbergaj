package danon.Cymbergaj;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.Engine;
import danon.Cymbergaj.Model.GameObject;
import danon.Cymbergaj.Model.World.Character.Fireball;
import danon.Cymbergaj.Model.World.Character.Wall;
import danon.Cymbergaj.Model.World.Control.ArrowsControlKeys;
import danon.Cymbergaj.Model.World.Control.Spaceship;
import danon.Cymbergaj.Model.World.Control.WsadControlKeys;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Window;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public final class Application {

    public static final double SCALE = 45.0; //  The scale 45 pixels per meter
    public static final double NANO_TO_BASE = 1.0e9;

    private final Engine engine = new Engine();
    private final Window window;
    private final World world = new World();
    private final Game game;

    private Application() {
        Settings settings = new Settings("Cymbergaj | Best 2D game jk", new Size(1080, 720));

        window = new Window(settings, event -> engine.stop());
        game = new Game(settings.getSize());

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
                            game.pointForLeft();
                        }
                        if (Objects.equals(user1, "right") || Objects.equals(user2, "right")) {
                            game.pointForRight();
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

        ImagesRepository images = new ImagesRepository();
        images.load();

        window.addRenderer(wall1.getRenderer(images));
        window.addRenderer(wall2.getRenderer(images));
        window.addRenderer(floor1.getRenderer(images));
        window.addRenderer(floor2.getRenderer(images));
        window.addRenderer(player1.getRenderer(images));
        window.addRenderer(player2.getRenderer(images));
        window.addRenderer(game.getRenderer(images));

        engine.addUpdateListener(player1);
        engine.addUpdateListener(player2);
    }

    public void start() {
        engine.addUpdateListener(world::update);
        engine.addRenderListener(window::render);

        window.show();
        engine.start();
    }

    public static void main(String[] args) {

        try {
            File file = new File("res/LookAtMyHorse.wav");
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip.open(ais);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        new Application().start();
    }
}