package gcyganek;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReader implements Runnable {

    private final Socket socket;
    private final BufferedReader in;
    private final Client client;

    public MessageReader(Socket socket, Client client) throws IOException {
        this.socket = socket;
        this.client = client;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                String msg = in.readLine();
                System.out.println(msg);
            }
        } catch (IOException e) {
            client.closeApp("Connection to the server has been lost. Closing the app. Press ENTER to end the process");
        }
    }

    public void closeReader() {
        try {
            if (in != null) in.close();
            if (!socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
