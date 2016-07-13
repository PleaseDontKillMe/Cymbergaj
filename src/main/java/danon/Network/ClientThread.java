package danon.Network;

import java.net.*;
import java.io.*;

class ClientThread extends Thread {
    private final Socket socket;
    private final Client client;
    private ObjectInputStream streamIn;

    ClientThread(Client client, Socket socket) {
        this.client = client;
        this.socket = socket;
    }

    void open() {
        try {
            streamIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
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
                TextMessage textMessage = (TextMessage) streamIn.readObject();
                client.handle(textMessage.getMessage());
            } catch (IOException ioe) {
                System.out.println("Listening error: " + ioe.getMessage());
                client.finnish();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}