package danon.Network.Client;

import danon.Config.log.Logger;
import danon.Cymbergaj.Application;
import danon.Cymbergaj.Config.RuntimeConfigFrame;
import danon.Cymbergaj.Config.StartupConfiguration;
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
    private final StartupConfiguration config;

    private Socket socket;
    private ObjectOutputStream streamOut;
    private ClientThread clientThread;


    private Thread applicationThread = new Thread(this);
    private final SocketControlKeys socketControlKeys = new SocketControlKeys();

    private Application application;
    private volatile Spaceship player1, player2;
    private volatile char myPlayer;

    public static void main(String[] args) throws Exception {
        RuntimeConfigFrame frame = new RuntimeConfigFrame();

        Logger.log("siema");

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
    }

    private void start() throws IOException {
        socket = new Socket(config.getHost(), Server.PORT);
        streamOut = new ObjectOutputStream(socket.getOutputStream());

        sendIntroduceMessage();

        clientThread = new ClientThread(this,
                new ObjectInputStream(new BufferedInputStream(socket.getInputStream())));
        clientThread.start();
    }

    private void sendIntroduceMessage() throws IOException {
        streamOut.writeObject(new IntroduceMessage(config.getUsername()));
        streamOut.flush();
    }

    /*
     * This is invoked only from clientThread#run
     */
    void handle(Message message) {
        if (message instanceof StartMessage) {
            play((StartMessage) message);
            applicationThread.start();
        }
        if (message instanceof KeyMessage) {
            if (((KeyMessage) message).getPlayer() != myPlayer) {
                socketControlKeys.acceptKeyChange((KeyMessage) message);
            }
        }
        if (message instanceof QuitMessage) {
            System.out.println("Good bye. Press RETURN to exit ...");
            finnish();
        }
    }

    /*
     * This is invoked from clientThread#run
     */
    void finnish() {
        application.stop();

        try {
            streamOut.close();
            socket.close();
        } catch (IOException ignored) {
        }
        clientThread.pleaseStop();
    }

    @Override
    public void run() {
        application.start();
    }

    private void play(StartMessage message) {
        myPlayer = message.getPlayerTeam();

        switch (myPlayer) {
            case 'L':
                player1 = new Spaceship(new WsadControlKeys(), new SocketKeys(streamOut, 'L'));
                player2 = new Spaceship(socketControlKeys, new Keys());
                break;
            case 'R':
                player1 = new Spaceship(socketControlKeys, new Keys());
                player2 = new Spaceship(new WsadControlKeys(), new SocketKeys(streamOut, 'R'));
                break;
        }

        application = new Application(player1, player2, config.getUsername());
        application.addWindowKeyListener(player1);
        application.addWindowKeyListener(player2);
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
