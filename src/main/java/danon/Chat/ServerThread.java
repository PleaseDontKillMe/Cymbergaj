package danon.Chat;

import java.net.*;
import java.io.*;

class ServerThread extends Thread {
    private Server parentServer;
    private Socket socket;
    private int ID;
    private DataInputStream streamIn;
    private DataOutputStream streamOut;

    ServerThread(Server parentServer, Socket socket) {
        super();
        this.parentServer = parentServer;
        this.socket = socket;
        ID = socket.getPort();
    }

    void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public void run() {
        System.out.println("Server Thread " + ID + " running.");
        while (true) {
            try {
                parentServer.handle(ID, streamIn.readUTF());
            } catch (IOException ioe) {
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                parentServer.remove(ID);
                interrupt();
            }
        }
    }

    void send(String msg) {
        try {
            streamOut.writeUTF(msg);
            streamOut.flush();
        } catch (IOException ioe) {
            System.out.println(ID + " ERROR sending: " + ioe.getMessage());
            parentServer.remove(ID);
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
