package danon.Cymbergaj;

import danon.Network.Message;
import danon.Network.StartMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.net.ServerSocket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server implements Runnable {
    static final int PORT = 9801;

    private final List<ServerThread> serverThreads = new CopyOnWriteArrayList<>();
    private final ServerSocket server;
    private final Thread thread;

    private final ServerPanel panel = new ServerPanel();

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
        panel.showWindow();
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Server is Running...");
        while (!thread.isInterrupted()) {
            try {
                ServerThread playerX = new ServerThread(this, server.accept());
                playerX.open();
                System.out.println("Accepted first " + playerX.toString());
                serverThreads.add(playerX);
                panel.updateList(serverThreads);

                ServerThread playerO = new ServerThread(this, server.accept());
                playerO.open();
                System.out.println("Accepted both" + playerO.toString());
                serverThreads.add(playerO);
                panel.updateList(serverThreads);

                playerX.send(0, new StartMessage('L'));
                playerO.send(0, new StartMessage('R'));

                playerX.start();
                playerO.start();
                System.out.println("Started game");
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
        panel.updateList(serverThreads);
        try {
            toTerminate.close();
        } catch (IOException ioe) {
            System.out.println("Error closing thread: " + ioe);
        }
        toTerminate.interrupt();
    }

    private synchronized void closeServer() {
        thread.interrupt();
        serverThreads.forEach(thread -> {
            try {
                thread.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private class ServerPanel extends JFrame {
        private final java.awt.List list = new java.awt.List();

        synchronized void updateList(List<ServerThread> threads) {
            list.removeAll();
            threads.forEach(thread -> list.add(thread.toString()));
        }

        void showWindow() {
            initializeWindow();
            setVisible(true);
        }

        private void initializeWindow() {
            setTitle("Server");
            setSize(new Dimension(300, 400));
            setLocationRelativeTo(null);
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    closeServer();
                    e.getWindow().dispose();
                }
            });

            setLayout(new BorderLayout());
            getContentPane().add(list, "Center");
            list.setEnabled(false);
        }


    }
}
