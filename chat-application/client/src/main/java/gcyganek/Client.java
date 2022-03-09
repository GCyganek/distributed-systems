package gcyganek;

import java.io.IOException;
import java.net.Socket;


public class Client {

    private final String hostName;
    private final int port;

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void run() {
        Socket socket = null;
        try {
            socket = new Socket(hostName, port);

            Thread messageReader = new Thread(new MessageReader(socket));
            Thread messageWriter = new Thread(new MessageWriter(socket));

            messageReader.start();
            messageWriter.start();

            System.out.println("Connected to the server, you can start chatting!");

            messageReader.join();
            messageWriter.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            close(socket);
        }
    }

    private void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
