package danon.Network.Client;


import danon.Network.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

class ClientThread extends Thread {
    private final Client parentClient;
    private final ObjectInputStream streamIn;

    ClientThread(Client parentClient, ObjectInputStream streamIn) {
        this.parentClient = parentClient;
        this.streamIn = streamIn;
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
        while (!isInterrupted()) {
            try {
                Message message = (Message) streamIn.readObject();
                parentClient.handle(message);
            } catch (IOException ioe) {
                System.out.println("Listening error: " + ioe.getMessage());
                parentClient.finnish();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                parentClient.finnish();
            }
        }
    }
}