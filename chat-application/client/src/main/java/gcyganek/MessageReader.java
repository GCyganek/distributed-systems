package gcyganek;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReader implements Runnable {

    private final Socket socket;
    private BufferedReader in;

    public MessageReader(Socket socket) {
        this.socket = socket;
        initializeInput();
    }

    private void initializeInput() {
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                String msg = in.readLine();
                System.out.println(msg);
            }
        } catch (IOException e) {
            System.out.println("Connection to the server has been lost");
        } finally {
            closeReader();
        }
    }

    private void closeReader() {
        try {
            if (in != null) in.close();
            if (!socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
