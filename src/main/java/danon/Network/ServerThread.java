package danon.Network;

import java.net.*;
import java.io.*;

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
        send("Welcome :)");
        while (!isInterrupted()) {
            try {
                TextMessage textMessage = (TextMessage) streamIn.readObject();
                parentServer.handle(ID, textMessage.getMessage());
            } catch (IOException ioe) {
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                parentServer.removeClient(this);
                this.interrupt();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    void send(String msg) {
        try {
            streamOut.writeObject(new TextMessage(msg));
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
