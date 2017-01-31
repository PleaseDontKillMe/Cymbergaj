package danon.Network.Client;

import danon.Cymbergaj.Application;
import danon.Cymbergaj.Config.StartupConfiguration;
import danon.Cymbergaj.Config.RuntimeConfigFrame;
import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.Model.World.Control.*;
import danon.Network.Message.*;
import danon.Network.Server.Server;
import org.dyn4j.geometry.Transform;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
    private Socket socket;
    private Thread thread;
    private ObjectOutputStream streamOut;
    private ClientThread clientThread;
    private final SocketControlKeys socketControlKeys;

    private Application application;
    private Spaceship player1, player2;
    private char myPlayer;
    private final String serverAddress;
    private final StartupConfiguration config;

    public static void main(String[] args) throws Exception {
        RuntimeConfigFrame frame = new RuntimeConfigFrame();

        frame.getRuntimeConfig(startupConfiguration -> new Thread(() -> {
            if (startupConfiguration.isNetwork()) {
                startNetworkGame(startupConfiguration);
            } else {
                startLocalGame();
            }
        }).start());
    }

    private static void startNetworkGame(StartupConfiguration config) {
        try {
            System.out.println("I'm " + config.getUsername());
            new Client(config).start();
        } catch (IOException e) {
            System.out.println("Error connecting");
        }
    }

    private Client(StartupConfiguration config) {
        this.config = config;
        this.serverAddress = config.getHost();
        this.socketControlKeys = new SocketControlKeys();
    }

    private void start() throws IOException {
        socket = new Socket(serverAddress, Server.PORT);
        streamOut = new ObjectOutputStream(socket.getOutputStream());

        sendIntroduceMessage();

        clientThread = new ClientThread(this, new ObjectInputStream(new BufferedInputStream(socket.getInputStream())));
        clientThread.start();

        thread = new Thread(this);
    }

    private void sendIntroduceMessage() throws IOException {
        streamOut.writeObject(new IntroduceMessage(config.getUsername()));
        streamOut.flush();
    }

    void handle(Message message) {
        if (message instanceof StartMessage) {
            play((StartMessage) message);
            if (thread.isAlive()) {
                throw new RuntimeException("Thread is already alive damn it");
            } else {
                thread.start();
                myPlayer = ((StartMessage) message).getPlayerTeam();
            }
        } else if (message instanceof KeyMessage) {
            if (((KeyMessage) message).getPlayer() != myPlayer) {
                socketControlKeys.acceptKeyChange((KeyMessage) message);
            }
        } else if (message instanceof QuitMessage) {
            System.out.println("Good bye. Press RETURN to exit ...");
            finnish();
        } else if (message instanceof PositionMessage) {
            PositionMessage positionMessage = (PositionMessage) message;
            Transform transform = new Transform();
            transform.setTranslation(positionMessage.getPositionX(), positionMessage.getPositionY());
            if (positionMessage.getPlayer() == myPlayer) {
                player1.setTransform(transform);
            } else {
                player2.setTransform(transform);
            }
        } else {
            System.out.println(message.toString());
        }
    }

    @Override
    public void run() {
        application.start();
    }

    private void play(StartMessage message) {
        System.out.println("Got welcome message");
        switch (message.getPlayerTeam()) {
            case 'L':
                player1 = new Spaceship(new WsadControlKeys(), new SocketKeys(streamOut, 'L'));
                player2 = new Spaceship(socketControlKeys, new Keys());
                break;
            case 'R':
                player1 = new Spaceship(socketControlKeys, new Keys());
                player2 = new Spaceship(new WsadControlKeys(), new SocketKeys(streamOut, 'R'));
                break;
            default:
                throw new RuntimeException("Unexpected player name from server");
        }

        application = new Application(player1, player2, config.getUsername());
        application.addWindowKeyListener(player1);
        application.addWindowKeyListener(player2);
    }

    void finnish() {
        thread.interrupt();

        try {
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
        clientThread.interrupt();
        clientThread.close();
    }

    private static void startLocalGame() {
        Spaceship player1 = new Spaceship(new WsadControlKeys());
        Spaceship player2 = new Spaceship(new ArrowsControlKeys());
        Application application = new Application(player1, player2, "");
        application.addWindowKeyListener(player1);
        application.addWindowKeyListener(player2);
        application.start();
    }
}
