package danon.Cymbergaj;

import danon.Network.KeyMessage;
import danon.Network.Message;
import danon.Network.QuitMessage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ServerThread extends Thread {
    private final Server parentServer;
    private final Socket socket;
    private int ID;
    private ObjectInputStream streamIn;
    private ObjectOutputStream streamOut;

    ServerThread(Server parentServer, Socket socket) {
        super();
        this.parentServer = parentServer;
        this.socket = socket;
        this.ID = socket.getPort();
    }

    void open() throws IOException {
        streamIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        streamOut = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Message message = (Message) streamIn.readObject();

                if (message instanceof KeyMessage) {
                    parentServer.handle(ID, message);
                }

                if (message instanceof QuitMessage) {
                    socket.close();
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("ServerThread died: " + e.getMessage() + " | " + e.getLocalizedMessage());
            this.interrupt();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    void send(int ID, Message message) {
        try {
            streamOut.writeObject(message);
            streamOut.flush();
        } catch (IOException ioe) {
            System.out.println(ID + " ERROR sending: " + ioe.getMessage());
            parentServer.removeClient(this);
            interrupt();
        }
    }

    int getID() {
        return ID;
    }

    void close() throws IOException {
        if (socket != null) socket.close();
        if (streamIn != null) streamIn.close();
        if (streamOut != null) streamOut.close();
    }
}
