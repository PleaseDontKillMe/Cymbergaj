package danon.Cymbergaj;

import danon.Cymbergaj.Config.GetConfigListener;
import danon.Cymbergaj.Config.RuntimeConfig;
import danon.Cymbergaj.Config.RuntimeConfigFrame;
import danon.Cymbergaj.Model.World.Character.Spaceship;
import danon.Cymbergaj.Model.World.Control.ArrowsControlKeys;
import danon.Cymbergaj.Model.World.Control.ControlKeys;
import danon.Cymbergaj.Model.World.Control.SocketControlKeys;
import danon.Cymbergaj.Model.World.Control.WsadControlKeys;

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
    private SocketControlKeys socketControlKeys = new SocketControlKeys();

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
    }

    private void play() throws Exception {
        try {
            System.out.println("Waiting for welcome message...");
            String response = in.readLine();
            if (response.startsWith("WELCOME")) {
                ControlKeys leftControl, rightControl;
                switch (response.charAt(8)) {
                    case 'L':
                        leftControl = new WsadControlKeys();
                        rightControl = socketControlKeys;
                        break;
                    case 'R':
                        leftControl = socketControlKeys;
                        rightControl = new WsadControlKeys();
                        break;
                    default:
                        throw new RuntimeException("Bieda");
                }
                Spaceship player1 = new Spaceship(leftControl), player2 = new Spaceship(rightControl);
                Application application = new Application(player1, player2);
                application.start();
            }
            while (true) {
                response = in.readLine();
                if (response.startsWith("KEYS")) {
                    String direction = response.substring(5, 11);
                    switch (direction) {
                        case "UP____0":
                            break;
                        case "DOWN__0":
                            break;
                        case "LEFT__0":
                            break;
                        case "RIGHT_0":
                            break;
                        case "UP____1":
                            break;
                        case "DOWN__1":
                            break;
                        case "LEFT__1":
                            break;
                        case "RIGHT_1":
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
