package danon.Network.Client;

import danon.Cymbergaj.Application;
import danon.Cymbergaj.Config.GetConfigListener;
import danon.Cymbergaj.Config.RuntimeConfig;
import danon.Cymbergaj.Config.RuntimeConfigFrame;
import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.Model.World.Control.*;
import danon.Network.Message.*;
import danon.Network.Server.Server;

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
    private char myPlayer;
    private final String serverAddress;
    private final RuntimeConfig config;

    private static void startNetworkGame(RuntimeConfig config) {
        try {
            System.out.println("I'm " + config.getUsername());
            Client client = new Client(config);
            client.start();
        } catch (IOException e) {
            System.out.println("Error connecting");
        }
    }

    private Client(RuntimeConfig config) {
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
            if (!thread.isAlive()) {
                thread.start();
                myPlayer = ((StartMessage) message).getPlayerTeam();
            } else {
                throw new RuntimeException("Thread is already alive damn it");
            }
        } else if (message instanceof KeyMessage) {
            if (((KeyMessage) message).getPlayer() != myPlayer) {
                socketControlKeys.acceptKeyChange((KeyMessage) message);
            }
        } else if (message instanceof QuitMessage) {
            System.out.println("Good bye. Press RETURN to exit ...");
            finnish();
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
        Spaceship player1, player2;
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

    public static void main(String[] args) throws Exception {
        GetConfigListener listener = config -> {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    if (config.isNetwork()) {
                        startNetworkGame(config);
                    } else {
                        startLocalGame();
                    }
                }
            };
            thread.start();
        };
        RuntimeConfigFrame frame = new RuntimeConfigFrame();
        frame.getRuntimeConfig(listener);
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
