package danon.Network.Server;

import danon.Network.Message.IntroduceMessage;
import danon.Network.Message.KeyMessage;
import danon.Network.Message.Message;
import danon.Network.Message.QuitMessage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ServerThread extends Thread {
    private final Server parentServer;
    private final Socket socket;
    private final int ID;

    private ObjectInputStream streamIn;
    private ObjectOutputStream streamOut;

    private volatile String clientName = "";
    private volatile boolean shouldStop = false;

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
            while (!shouldStop) {
                Message message = (Message) streamIn.readObject();

                if (message instanceof KeyMessage) {
                    parentServer.handle(ID, message);
                }

                if (message instanceof QuitMessage) {
                    pleaseClose();
                    return;
                }

                if (message instanceof IntroduceMessage) {
                    IntroduceMessage introMessage = (IntroduceMessage) message;
                    clientName = introMessage.getName();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            pleaseClose();
        }
    }

    void send(int ID, Message message) {
        try {
            streamOut.writeObject(message);
            streamOut.flush();
        } catch (IOException ioe) {
            System.out.println(ID + " ERROR sending: " + ioe.getMessage());
            pleaseClose();
        }
    }

    int getID() {
        return ID;
    }

    void pleaseClose() {
        shouldStop = true;
        parentServer.removeClient(this);
        try {
            socket.close();
            streamIn.close();
            streamOut.close();
        } catch (IOException ignored) {
        }
    }

    @Override
    public String toString() {
        return clientName + " {" +
                "port=" + ID +
                "/" + socket.getLocalPort() +
                "  " + socket.getLocalAddress() +
                "}";
    }
}
