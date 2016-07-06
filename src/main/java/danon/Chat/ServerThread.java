package danon.Chat;

import java.net.*;
import java.io.*;

class ServerThread extends Thread {
    private Server parentServer;
    private Socket socket;
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
        streamIn = new ObjectInputStream(socket.getInputStream());
        streamOut = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                parentServer.handle(ID, streamIn.readUTF());
            } catch (IOException ioe) {
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                parentServer.removeClient(this);
                this.interrupt();
            }
        }
    }

    void send(String msg) {
        try {
            streamOut.writeUTF(msg);
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
