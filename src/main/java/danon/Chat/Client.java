package danon.Chat;

import java.net.*;
import java.io.*;

public class Client implements Runnable {
    private Socket socket;
    private Thread thread = new Thread(this);
    private BufferedReader console;
    private DataOutputStream streamOut;
    private ClientThread client;

    public static void main(String args[]) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java Client host port");
            return;
        }

        Client client = new Client(args[0], Integer.parseInt(args[1]));
        client.start();
    }

    private Client(String serverName, int serverPort) {
        System.out.println("Establishing connection. Please wait ...");
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

        client = new ClientThread(this, socket);
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
        thread.stop();

        try {
            if (console != null) console.close();
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
        client.close();
        client.stop();
    }
}