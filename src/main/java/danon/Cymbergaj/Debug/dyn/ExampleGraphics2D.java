package danon.Cymbergaj.Debug.dyn;

import danon.Cymbergaj.Model.World.Control.ArrowsControlKeys;
import danon.Cymbergaj.Model.World.Control.WsadControlKeys;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;


public class ExampleGraphics2D extends JFrame {

    public static final double SCALE = 45.0; //  The scale 45 pixels per meter
    public static final double NANO_TO_BASE = 1.0e9;

    protected Canvas canvas = new Canvas();
    protected World world;
    protected boolean stopped = false;
    protected long last;
    private Dimension size;

    public ExampleGraphics2D() {
        super("Graphics2D Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
                super.windowClosing(e);
            }
        });

        size = new Dimension(1000, 800);

        canvas.setPreferredSize(size);
        canvas.setMinimumSize(size);
        canvas.setMaximumSize(size);

        add(this.canvas);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

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
        GameObject ball = new Ball();
        BodyFixture ballFixture = new BodyFixture(new Circle(0.3));
        ballFixture.setRestitution(1.0);
        ball.addFixture(ballFixture);
        ball.setMass(MassType.NORMAL);
        ball.applyForce(new Vector2(-150.0, 0.0));
        ball.setLinearDamping(0.05);
        this.world.addBody(ball);

        // players
        Player player1 = new Player(new WsadControlKeys()), player2 = new Player(new ArrowsControlKeys());
        player1.addFixture(new Circle(0.7));
        player1.setMass(MassType.NORMAL);
        player1.translate(-9.0, 0.0);
        player2.addFixture(new Circle(0.7));
        player2.translate(9.0, 0.0);
        player2.setMass(MassType.NORMAL);
        this.world.addBody(player1);
        this.world.addBody(player2);

        this.addKeyListener(player1);
        this.addKeyListener(player2);
    }

    public void start() {
        last = System.nanoTime();
        canvas.setIgnoreRepaint(true);
        canvas.createBufferStrategy(2);
        Thread thread = new Thread() {
            public void run() {
                while (isStarted()) {
                    gameLoop();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    protected void gameLoop() {
        Graphics2D g = (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();

        // before we render everything im going to flip the y axis and move the
        // origin to the center (instead of it being in the top left corner)
        AffineTransform yFlip = AffineTransform.getScaleInstance(1, -1);
        AffineTransform move = AffineTransform.getTranslateInstance(size.getWidth()/2, -size.getHeight()/2);
        g.transform(yFlip);
        g.transform(move);

        this.render(g);
        g.dispose();

        BufferStrategy strategy = canvas.getBufferStrategy();
        if (!strategy.contentsLost()) {
            strategy.show();
        }

        // Sync the display on some systems.
        // (on Linux, this fixes event queue problems)
        Toolkit.getDefaultToolkit().sync();

        long time = System.nanoTime();
        long elapsed = time - this.last;
        this.last = time;
        double elapsedTimeInSeconds = elapsed / NANO_TO_BASE;
        this.world.update(elapsedTimeInSeconds);
    }

    protected void render(Graphics2D canvas) {
        canvas.setColor(Color.WHITE);
        canvas.fillRect((int)(-size.getWidth()/2), (int) (-size.getHeight()/2), (int)size.getWidth(), (int)size.getHeight());

        for (int i = 0; i < this.world.getBodyCount(); i++) {
            GameObject go = (GameObject) this.world.getBody(i);
            go.render(canvas);
        }
    }

    public synchronized void stop() {
        stopped = true;
    }

    public synchronized boolean isStarted() {
        return !stopped;
    }

    public static void main(String[] args) throws IllegalAccessException {
        ExampleGraphics2D window = new ExampleGraphics2D();
        window.setVisible(true);
        window.start();
    }
}