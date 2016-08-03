package danon.Cymbergaj.Application;

import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.*;
import danon.Cymbergaj.Model.World.Character.*;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

public class ServerApplication implements Application {
    private final Engine engine = new Engine();
    private final World world = new World();
    private final Game game;

    private final Spaceship playerLeft;
    private final Spaceship playerRight;

    ServerApplication(Spaceship playerLeft, Spaceship playerRight, String name) {
        Settings settings = new Settings("Cymbergaj | Best 2D game jk " + name, new Size(1080, 600));

        this.game = new Game(settings.getSize());

        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
    }

    @Override
    public void open() {
        initializeWorld();
        engine.addUpdateListener(world::update);
    }

    @Override
    public void start() {
        engine.start();
    }

    @Override
    public void stop() {
        engine.stop();
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

        engine.addUpdateListener(playerLeft);
        engine.addUpdateListener(playerRight);
        engine.addUpdateListener(game);

        world.addListener(new QuiteGamePointsCounter(game));
    }
}
