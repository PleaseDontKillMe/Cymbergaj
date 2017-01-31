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

    @Override
    public void interrupt() {
        super.interrupt();
        throw new RuntimeException("Intertupted :/ Nah nah");
    }

    public void pleaseStop() {
        shouldStop = true;
    }

    private void close() {
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
