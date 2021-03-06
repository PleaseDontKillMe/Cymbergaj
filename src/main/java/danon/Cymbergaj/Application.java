package danon.Cymbergaj;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.*;
import danon.Cymbergaj.Model.World.Character.*;
import danon.Cymbergaj.View.GameWindow;
import danon.Cymbergaj.View.Renderer.FireballRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import danon.Cymbergaj.View.Renderer.SpaceshipRenderer;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

import java.awt.event.KeyListener;

public final class Application {
    public final static int SCALE = 45;

    private final EngineFactory engineFactory;
    private final EngineExecutor engineExecutor;
    private final GameWindow gameWindow;
    private final World world = new World();
    private final Game game;
    private final SoundsRepository sounds = new SoundsRepository();
    private final ImagesRepository images = new ImagesRepository();

    private final Spaceship playerLeft;
    private final Spaceship playerRight;

    public Application(Spaceship playerLeft, Spaceship playerRight, String name) {
        Settings settings = new Settings("Cymbergaj | Best 2D game jk " + name, new Size(1080, 600));

        this.gameWindow = new GameWindow(settings);
        this.game = new Game(settings.getSize());

        this.playerLeft = playerLeft;
        this.playerRight = playerRight;

        this.engineFactory = new EngineFactory();
        this.engineFactory.addUpdateListener(world::update);
        this.engineFactory.addRenderListener(gameWindow::render);

        load();

        this.engineExecutor = engineFactory.createExecutor();
        this.gameWindow.addCloseEventListener(closeEvent -> engineExecutor.stop());
    }

    private void load() {
        sounds.load();
        images.load();

        initializeWorld();

        game.onGameStart(() -> {
            sounds.lookAtMyHorse.setFramePosition(0);
            sounds.lookAtMyHorse.start();
        });
    }

    public void start() {
        gameWindow.show();
        sounds.play(sounds.lookAtMyHorse);
        engineExecutor.start();
    }

    public void stop() {
        engineExecutor.stop();
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
        playerLeft.translate(-9.0, 0.0);
        playerRight.translate(9.0, 0.0);

        // Added bodies to the world
        world.addBody(topFloor);
        world.addBody(bottomFloor);
        world.addBody(stopper1);
        world.addBody(stopper2);
        world.addBody(stopper3);
        world.addBody(stopper4);
        world.addBody(leftWall);
        world.addBody(rightWall);
        world.addBody(ball);
        world.addBody(playerLeft);
        world.addBody(playerRight);

        SpaceshipRenderer player1renderer = playerLeft.getRenderer(images);
        SpaceshipRenderer player2renderer = playerRight.getRenderer(images);
        FireballRenderer fireballrenderer = ball.getRenderer(images);

        gameWindow.addRenderer(game.getRenderer(images));
        gameWindow.addRenderer(game.getPointsRenderer());
        gameWindow.addRenderer(leftWall.getRenderer(images));
        gameWindow.addRenderer(rightWall.getRenderer(images));
        gameWindow.addRenderer(topFloor.getRenderer(images));
        gameWindow.addRenderer(bottomFloor.getRenderer(images));
        gameWindow.addRenderer(stopper1.getRenderer(images));
        gameWindow.addRenderer(stopper2.getRenderer(images));
        gameWindow.addRenderer(stopper3.getRenderer(images));
        gameWindow.addRenderer(stopper4.getRenderer(images));
        gameWindow.addRenderer(player1renderer);
        gameWindow.addRenderer(player2renderer);
        gameWindow.addRenderer(fireballrenderer);

        engineFactory.addUpdateListener(playerLeft);
        engineFactory.addUpdateListener(playerRight);
        engineFactory.addUpdateListener(game);
        engineFactory.addUpdateListener(player1renderer);
        engineFactory.addUpdateListener(player2renderer);
        engineFactory.addUpdateListener(fireballrenderer);

        world.addListener(new GamePointsCounter(game, sounds));
    }

    public void addWindowKeyListener(KeyListener listener) {
        gameWindow.addKeyListener(listener);
    }
}
