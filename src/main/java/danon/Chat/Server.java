package danon.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Server implements Runnable {
    static final int PORT = 9801;

    private final List<ServerThread> serverThreads = new ArrayList<>(MAX_CLIENTS);
    private final ServerSocket server;
    private final Thread thread;
    private final static int MAX_CLIENTS = 50;

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
                System.out.println("Waiting for a client ...");
                addThread(server.accept());
            } catch (IOException ioe) {
                System.out.println("Server accept error: " + ioe);
                thread.interrupt();
            }
        }
    }

    private void addThread(Socket socket) {
        if (serverThreads.size() < MAX_CLIENTS) {
            System.out.println("Client accepted: " + socket);
            ServerThread serverThread = new ServerThread(this, socket);
            try {
                serverThread.open();
                serverThread.start();
                serverThreads.add(serverThread);
            } catch (IOException ioe) {
                System.out.println("Error opening thread: " + ioe);
            }
        } else {
            System.out.println("Client refused: maximum " + MAX_CLIENTS + " reached.");
        }
    }

    synchronized void handle(int ID, String input) {
        if (input.equals(".bye")) {
            ServerThread thread = serverThreadById(ID);
            thread.send(".bye");
            removeClient(thread);
        } else {
            serverThreads.forEach(serverThread -> serverThread.send(ID + ": " + input));
        }
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

    private ServerThread serverThreadById(int ID) {
        Optional<ServerThread> found = serverThreads.stream()
                .filter(serverThread -> serverThread.getID() == ID)
                .findFirst();

        if (found.isPresent()) {
            return found.get();
        }

        throw new RuntimeException("ServerThread not found");
    }
}
