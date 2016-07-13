package danon.Cymbergaj;


import danon.Network.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

class ClientThread extends Thread {
    private Socket socket;
    private Client parentClient;
    private ObjectInputStream streamIn;

    ClientThread(Client parentClient, Socket socket) {
        this.parentClient = parentClient;
        this.socket = socket;
    }

    void open() {
        try {
            streamIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException exception) {
            System.out.println("Error getting input stream: " + exception);
            parentClient.finnish();
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
                parentClient.handle(message);
            } catch (IOException ioe) {
                System.out.println("Listening error: " + ioe.getMessage());
                parentClient.finnish();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}