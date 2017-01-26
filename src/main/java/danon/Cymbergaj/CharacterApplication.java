package danon.Cymbergaj;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.*;
import danon.Cymbergaj.Model.World.Character.Floor;
import danon.Cymbergaj.Model.World.Character.GameObject;
import danon.Cymbergaj.Model.World.Character.Stopper;
import danon.Cymbergaj.Model.World.Character.Wall;
import danon.Cymbergaj.Model.World.Control.WsadControlKeys;
import danon.Cymbergaj.View.GameWindow;
import danon.Cymbergaj.View.Renderer.CharacterRenderer;
import danon.Cymbergaj.View.Renderer.ImagesRepository;
import org.dyn4j.dynamics.World;

import java.awt.event.KeyListener;


public final class CharacterApplication {
    private final EngineFactory engineFactory;
    private final EngineExecutor engineExecutor;
    private final GameWindow gameWindow;
    private final World world = new World();
    private final Game game;
    private final SoundsRepository sounds = new SoundsRepository();
    private final ImagesRepository images = new ImagesRepository();

    private final Character character;

    public static void main(String[] args) {
        Character character = new Character(new WsadControlKeys());
        CharacterApplication app = new CharacterApplication(character);
        app.addWindowKeyListener(character);
        app.start();
    }

    private CharacterApplication(Character character) {
        Settings settings = new Settings("Character test | Best 2D game jk ", new Size(1080, 600));

        this.gameWindow = new GameWindow(settings);
        this.game = new Game(settings.getSize());

        this.engineFactory = new EngineFactory();
        this.engineFactory.addUpdateListener(world::update);
        this.engineFactory.addRenderListener(gameWindow::render);


        this.character = character;
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

    private void start() {
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


        // players
        character.translate(-9.0, 0.0);

        // Added bodies to the world
        world.addBody(topFloor);
        world.addBody(bottomFloor);
        world.addBody(stopper1);
        world.addBody(stopper2);
        world.addBody(stopper3);
        world.addBody(stopper4);
        world.addBody(leftWall);
        world.addBody(rightWall);
        world.addBody(character);

        CharacterRenderer characterRenderer = character.getRenderer(images);

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
        gameWindow.addRenderer(characterRenderer);

        engineFactory.addUpdateListener(character);
        engineFactory.addUpdateListener(game);
        engineFactory.addUpdateListener(characterRenderer);

        world.addListener(new GamePointsCounter(game, sounds));
    }

    private void addWindowKeyListener(KeyListener listener) {
        this.gameWindow.addKeyListener(listener);
    }
}
