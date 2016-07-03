package danon.Cymbergaj;

import danon.Cymbergaj.Config.GetConfigListener;
import danon.Cymbergaj.Config.RuntimeConfig;
import danon.Cymbergaj.Config.RuntimeConfigFrame;
import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.Model.World.Control.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private SocketControlKeys socketControlKeys;

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
        Application application = new Application(player1, player2);
        application.addWindowKeyListener(player1);
        application.addWindowKeyListener(player2);
        application.start();
    }

    private static void startNetworkGame(RuntimeConfig config) {
        try {
            Client client = new Client(config.getHost());
            client.play();
        } catch (Exception ignored) {
            System.out.println("Error connecting");
        }
    }

    private Client(String serverAddress) throws Exception {
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        socketControlKeys = new SocketControlKeys();
    }

    private void play() throws Exception {
        try {
            System.out.println("Waiting for welcome message...");
            String response = in.readLine();
            if (response.startsWith("WELCOME")) {
                Spaceship player1, player2;
                switch (response.charAt(8)) {
                    case 'L':
                        player1 = new Spaceship(new WsadControlKeys(), new SocketKeys(out));
                        player2 = new Spaceship(socketControlKeys, new Keys());
                        break;
                    case 'R':
                        player1 = new Spaceship(socketControlKeys, new Keys());
                        player2 = new Spaceship(new WsadControlKeys(), new SocketKeys(out));
                        break;
                    default:
                        throw new RuntimeException("Bieda");
                }

                Application application = new Application(player1, player2);
                application.addWindowKeyListener(player1);
                application.addWindowKeyListener(player2);
                application.start();
            }
            while (true) {
                response = in.readLine();
                if (response.startsWith("KEYS")) {
                    String direction = response.substring(5, 11);
                    switch (direction) {
                        case "UP____0":
                            socketControlKeys.keys.setUp(false);
                            break;
                        case "DOWN__0":
                            socketControlKeys.keys.setDown(false);
                            break;
                        case "LEFT__0":
                            socketControlKeys.keys.setLeft(false);
                            break;
                        case "RIGHT_0":
                            socketControlKeys.keys.setRight(false);
                            break;
                        case "UP____1":
                            socketControlKeys.keys.setUp(true);
                            break;
                        case "DOWN__1":
                            socketControlKeys.keys.setDown(true);
                            break;
                        case "LEFT__1":
                            socketControlKeys.keys.setLeft(true);
                            break;
                        case "RIGHT_1":
                            socketControlKeys.keys.setRight(true);
                            break;
                    }
                }
                if (response.startsWith("BALL")) {

                }
                if (response.startsWith("YOU")) {

                }
                if (response.startsWith("HIM")) {

                }
            }
            // out.println("QUIT");
            //System.out.println("Exiting...");
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

}
