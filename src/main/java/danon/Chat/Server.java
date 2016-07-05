package danon.Chat;

import java.net.*;
import java.io.*;

public class Server implements Runnable {
    static final int PORT = 9801;

    private ServerThread clients[] = new ServerThread[50];
    private ServerSocket server;
    private Thread thread;
    private int clientCount = 0;

    public static void main(String args[]) throws IOException {
        int port = PORT;
        System.out.println("Binding to port " + port + ", please wait  ...");

        Server server = new Server(new ServerSocket(port));
        server.start();
    }

    private Server(ServerSocket serverSocket) {
        this.server = serverSocket;
        thread = new Thread(this);
    }

    private void start() {
        thread.start();
    }

    @Override
    public void run() {
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
        if (clientCount < clients.length) {
            System.out.println("Client accepted: " + socket);
            clients[clientCount] = new ServerThread(this, socket);
            try {
                clients[clientCount].open();
                clients[clientCount].start();
                clientCount++;
            } catch (IOException ioe) {
                System.out.println("Error opening thread: " + ioe);
            }
        } else
            System.out.println("Client refused: maximum " + clients.length + " reached.");
    }

    synchronized void handle(int ID, String input) {
        if (input.equals(".bye")) {
            clients[findClientById(ID)].send(".bye");
            removeClient(ID);
        } else {
            for (int i = 0; i < clientCount; i++) {
                clients[i].send(ID + ": " + input);
            }
        }
    }

    synchronized void removeClient(int ID) {
        int pos = findClientById(ID);
        if (pos >= 0) {
            System.out.println("Removing client thread " + ID + " at " + pos);
            ServerThread toTerminate = clients[pos];
            if (pos < clientCount - 1) {
                for (int i = pos + 1; i < clientCount; i++)
                    clients[i - 1] = clients[i];
            }
            clientCount--;
            try {
                toTerminate.close();
            } catch (IOException ioe) {
                System.out.println("Error closing thread: " + ioe);
            }
            toTerminate.interrupt();
        }
    }

    private int findClientById(int ID) {
        for (int i = 0; i < clientCount; i++) {
            if (clients[i].getID() == ID) {
                return i;
            }
        }
        return -1;
    }
}