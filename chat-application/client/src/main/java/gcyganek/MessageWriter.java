package gcyganek;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MessageWriter implements Runnable{

    private final Socket socket;
    private final Scanner systemIn;
    private PrintWriter out;

    public MessageWriter(Socket socket) {
        this.socket = socket;
        this.systemIn = new Scanner(System.in);
        initializeOutputAndSendUsername();
    }

    private void initializeOutputAndSendUsername() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Enter your username:");
            String username = systemIn.nextLine();
            out.println(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (socket.isConnected() && !socket.isClosed()) {
            String msg = systemIn.nextLine();
            out.println(msg);
        }
        closeWriter();
    }

    private void closeWriter() {
        try {
            if (out != null) out.close();
            if (!socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
