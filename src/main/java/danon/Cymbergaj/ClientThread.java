package danon.Cymbergaj;


import danon.Chat.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private Client client;
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

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Message message = (Message) streamIn.readObject();
                client.handle(message);
            } catch (IOException ioe) {
                System.out.println("Listening error: " + ioe.getMessage());
                client.finnish();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}