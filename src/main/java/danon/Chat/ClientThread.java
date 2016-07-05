package danon.Chat;

import java.net.*;
import java.io.*;

class ClientThread extends Thread {
    private Socket socket;
    private Client client;
    private DataInputStream streamIn = null;

    ClientThread(Client client, Socket socket) {
        this.client = client;
        this.socket = socket;
        open();
        start();
    }

    private void open() {
        try {
            streamIn = new DataInputStream(socket.getInputStream());
        } catch (IOException exception) {
            System.out.println("Error getting input stream: " + exception);
            client.finnish();
        }
    }

    void close() {
        try {
            streamIn.close();
        } catch (IOException ioe) {
            System.out.println("Error closing input stream: " + ioe);
        }
    }

    public void run() {
        while (!this.isInterrupted()) {
            try {
                client.handle(streamIn.readUTF());
            } catch (IOException ioe) {
                System.out.println("Listening error: " + ioe.getMessage());
                client.finnish();
            }
        }
    }
}