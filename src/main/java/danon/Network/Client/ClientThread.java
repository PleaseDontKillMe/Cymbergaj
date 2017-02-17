package danon.Network.Client;

import danon.Network.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

class ClientThread extends Thread {
    private final Client parentClient;
    private final ObjectInputStream streamIn;

    private volatile boolean shouldBeRunning = true;

    ClientThread(Client parentClient, ObjectInputStream streamIn) {
        this.parentClient = parentClient;
        this.streamIn = streamIn;
    }

    void pleaseStop() {
        shouldBeRunning = false;
    }

    @Override
    public void run() {
        while (shouldBeRunning) {
            try {
                Message message = (Message) streamIn.readObject();
                parentClient.handle(message);
            } catch (IOException | ClassNotFoundException e) {
                parentClient.finnish();
                break;
            }
        }
        close();
    }

    private void close() {
        try {
            streamIn.close();
        } catch (IOException ignored) {
        }
    }
}
