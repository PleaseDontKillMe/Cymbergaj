package danon.Chat;

import java.net.*;
import java.io.*;

public class Client implements Runnable {
    private Socket socket;
    private Thread thread;
    private BufferedReader console;
    private DataOutputStream streamOut;
    private ClientThread clientThread;

    public static void main(String args[]) throws IOException {
        if (args.length != 1) {
            System.out.println("Host needed");
            return;
        }

        System.out.println("Establishing connection. Please wait ...");
        Client client = new Client(args[0], Server.PORT);
        client.start();
    }

    private Client(String serverName, int serverPort) {
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {
        console = new BufferedReader(new InputStreamReader(System.in));
        streamOut = new DataOutputStream(socket.getOutputStream());

        clientThread = new ClientThread(this, socket);
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (thread != null) {
            try {
                streamOut.writeUTF(console.readLine());
                streamOut.flush();
            } catch (IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
                stop();
            }
        }
    }

    void handle(String msg) {
        if (msg.equals(".bye")) {
            System.out.println("Good bye. Press RETURN to exit ...");
            stop();
        } else
            System.out.println(msg);
    }

    void stop() {
        thread.interrupt();

        try {
            if (console != null) console.close();
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
        clientThread.close();
        clientThread.interrupt();
    }
}