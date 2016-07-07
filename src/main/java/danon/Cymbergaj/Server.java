package danon.Cymbergaj;

import danon.Chat.Message;
import danon.Chat.StartMessage;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.net.ServerSocket;

public class Server implements Runnable {
    static final int PORT = 9801;

    private List<ServerThread> serverThreads = new ArrayList<>(MAX_CLIENTS);
    private ServerSocket server;
    private Thread thread;
    private static final int MAX_CLIENTS = 50;

    public static void main(String[] args) throws IOException {
        int port = PORT;
        System.out.println("Binding to port " + port + ", please wait  ...");

        Server server = new Server(new ServerSocket(port));
        server.start();
    }

    private Server(ServerSocket serverSocket) {
        this.server = serverSocket;
        this.thread = new Thread(this);
    }

    private void start() {
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Server is Running...");
        while (!thread.isInterrupted()) {
            try {
                ServerThread playerX = new ServerThread(this, server.accept());
                playerX.open();
                System.out.println("Accepted first");

                ServerThread playerO = new ServerThread(this, server.accept());
                playerO.open();
                System.out.println("Accepted both");

                serverThreads.add(playerO);
                serverThreads.add(playerX);

                playerX.send(0, new StartMessage('L'));
                playerO.send(0, new StartMessage('R'));

                playerX.start();
                playerO.start();
            } catch (IOException e) {
                thread.interrupt();
                e.printStackTrace();
            }
        }
    }

    synchronized void handle(int ID, Message message) {
        serverThreads.forEach(playerThread -> playerThread.send(ID, message));
    }

    synchronized void removeClient(ServerThread toTerminate) {
        System.out.println("Removing client thread " + toTerminate.getID());
        serverThreads.remove(toTerminate);
        try {
            toTerminate.close();
        } catch (IOException ioe) {
            System.out.println("Error closing thread: " + ioe);
        }
        toTerminate.interrupt();
    }
}
