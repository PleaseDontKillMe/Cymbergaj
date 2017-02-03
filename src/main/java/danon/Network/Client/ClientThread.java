package danon.Network.Client;


import danon.Network.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

class ClientThread extends Thread {
    private final Client parentClient;
    private final ObjectInputStream streamIn;

    private volatile boolean shouldStop = false;

    ClientThread(Client parentClient, ObjectInputStream streamIn) {
        this.parentClient = parentClient;
        this.streamIn = streamIn;
    }

    void pleaseStop() {
        shouldStop = true;
        try {
            streamIn.close();
        } catch (IOException ioe) {
            System.out.println("Error closing input stream: " + ioe);
        }
    }

    @Override
    public void run() {
        while (!shouldStop) {
            try {
                Message message = (Message) streamIn.readObject();
                parentClient.handle(message);
            } catch (IOException | ClassNotFoundException e) {
                parentClient.finnish();
            }
        }
    }
}
