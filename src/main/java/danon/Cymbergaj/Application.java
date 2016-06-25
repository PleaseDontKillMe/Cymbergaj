package danon.Cymbergaj;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.*;
import danon.Cymbergaj.Model.World.Character.*;
import danon.Cymbergaj.Model.World.Control.ArrowsControlKeys;
import danon.Cymbergaj.Model.World.Control.WsadControlKeys;
import danon.Cymbergaj.View.PointsRenderer;
import danon.Cymbergaj.View.Renderer.*;
import danon.Cymbergaj.View.Window;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.*;


public final class Application {

    public static final double SCALE = 45.0; //  The scale 45 pixels per meter

    private final Engine engine = new Engine();
    private final Window window;
    private final World world = new World();
    private final Game game;
    private final SoundsRepository sounds = new SoundsRepository();
    private final ImagesRepository images = new ImagesRepository();

    private Application() {
        Settings settings = new Settings("Cymbergaj | Best 2D game jk", new Size(1080, 600));

        this.window = new Window(settings, closeEvent -> engine.stop());
        this.game = new Game(settings.getSize());
    }

    private void start() {
        sounds.load();
        images.load();

        initializeWorld();

        sounds.addStopListener(sounds.lookAtMyHorse, game::restartGame);
        game.onGameStart(() -> {
            sounds.lookAtMyHorse.setFramePosition(0);
            sounds.lookAtMyHorse.start();
        });

        engine.addUpdateListener(world::update);
        engine.addRenderListener(window::render);

        window.show();
        sounds.play(sounds.lookAtMyHorse);
        engine.start();
    }

    private void initializeWorld() {
        world.setGravity(World.ZERO_GRAVITY);

        // create the floors
        GameObject topFloor = new Floor(), bottomFloor = new Floor();
        topFloor.translate(0.0, -6.0);
        bottomFloor.translate(0.0, 6.0);

        // Stoppers blocking parts of gates
        GameObject stopper1 = new Stopper(), stopper2 = new Stopper(), stopper3 = new Stopper(), stopper4 = new Stopper();
        double x = 9.7, y = 4.25;
        stopper1.translate(-x, -y);
        stopper2.translate(x, -y);
        stopper3.translate(-x, y);
        stopper4.translate(x, y);

        // Vertical walls
        GameObject leftWall = new Wall("left"), rightWall = new Wall("right");
        leftWall.translate(-10.0, 0);
        rightWall.translate(10.0, 0.0);

        // Fireball
        Fireball ball = new Fireball();
        ball.applyForce(new Vector2(-150.0, 0.0));

        // players
        Spaceship player1 = new Spaceship(new WsadControlKeys()), player2 = new Spaceship(new ArrowsControlKeys());
        player1.translate(-9.0, 0.0);
        player2.translate(9.0, 0.0);

        world.addBody(topFloor);
        world.addBody(bottomFloor);
        world.addBody(stopper1);
        world.addBody(stopper2);
        world.addBody(stopper3);
        world.addBody(stopper4);
        world.addBody(leftWall);
        world.addBody(rightWall);
        world.addBody(ball);
        world.addBody(player1);
        world.addBody(player2);

        window.addKeyListener(player1);
        window.addKeyListener(player2);

        SpaceshipRenderer player1renderer = player1.getRenderer(images);
        FireballRenderer fireballrenderer = ball.getRenderer(images);
        SpaceshipRenderer player2renderer = player2.getRenderer(images);

        window.addRenderer(game.getRenderer(images));
        window.addRenderer(game.getPointsRenderer());
        window.addRenderer(leftWall.getRenderer(images));
        window.addRenderer(rightWall.getRenderer(images));
        window.addRenderer(topFloor.getRenderer(images));
        window.addRenderer(bottomFloor.getRenderer(images));
        window.addRenderer(stopper1.getRenderer(images));
        window.addRenderer(stopper2.getRenderer(images));
        window.addRenderer(stopper3.getRenderer(images));
        window.addRenderer(stopper4.getRenderer(images));
        window.addRenderer(player1renderer);
        window.addRenderer(player2renderer);
        window.addRenderer(fireballrenderer);

        engine.addUpdateListener(player1);
        engine.addUpdateListener(player2);
        engine.addUpdateListener(game);
        engine.addUpdateListener(fireballrenderer);
        engine.addUpdateListener(player1renderer);
        engine.addUpdateListener(player2renderer);

        world.addListener(new GamePointsCounter(game, sounds));
    }

    public static void main(String[] args) {
        new Application().start();
    }
}