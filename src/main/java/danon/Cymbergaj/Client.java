package danon.Cymbergaj;

import danon.Chat.KeyMessage;
import danon.Chat.Message;
import danon.Chat.QuitMessage;
import danon.Chat.StartMessage;
import danon.Cymbergaj.Config.GetConfigListener;
import danon.Cymbergaj.Config.RuntimeConfig;
import danon.Cymbergaj.Config.RuntimeConfigFrame;
import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.Model.World.Control.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream streamOut;
    private ObjectInputStream streamIn;
    private ClientThread clientThread;
    private SocketControlKeys socketControlKeys;

    private boolean shouldStopLoop = false;
    private String serverAddress;
    private RuntimeConfig config;

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

    private static void startNetworkGame(RuntimeConfig config) {
        try {
            System.out.println("I'm " + config.getUsername());
            Client client = new Client(config);
            System.out.println("Going to open");
            client.open();
        } catch (IOException e) {
            System.out.println("Error connecting");
        }
    }

    private Client(RuntimeConfig config) throws IOException {
        this.config = config;
        this.serverAddress = config.getHost();
        socketControlKeys = new SocketControlKeys();
    }

    private void open() throws IOException {
        socket = new Socket(serverAddress, Server.PORT);
        streamOut = new ObjectOutputStream(socket.getOutputStream());

        clientThread = new ClientThread(this, socket);
        clientThread.open();
        clientThread.start();
    }

    void handle(Message message) {
        if (message instanceof StartMessage) {
            play((StartMessage) message);
        } else if (message instanceof KeyMessage) {
            socketControlKeys.acceptKeyChange((KeyMessage) message);
        } else if (message instanceof QuitMessage) {
            System.out.println("Good bye. Press RETURN to exit ...");
            finnish();
        } else {
            System.out.println(message.toString());
        }
    }

    private void play(StartMessage message) {
        System.out.println("Got welcome message");
        Spaceship player1, player2;
        switch (message.getPlayerTeam()) {
            case 'L':
                player1 = new Spaceship(new WsadControlKeys(), new SocketKeys(streamOut));
                player2 = new Spaceship(socketControlKeys, new Keys());
                break;
            case 'R':
                player1 = new Spaceship(socketControlKeys, new Keys());
                player2 = new Spaceship(new WsadControlKeys(), new SocketKeys(streamOut));
                break;
            default:
                throw new RuntimeException("Bieda");
        }

        Application application = new Application(player1, player2, config.getUsername());
        application.addWindowKeyListener(player1);
        application.addWindowKeyListener(player2);

        System.out.println("Got start message");
        application.start();
    }

    void finnish() {
        shouldStopLoop = true;

        try {
            if (streamIn != null) streamIn.close();
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
        clientThread.interrupt();
        clientThread.close();
    }
}
