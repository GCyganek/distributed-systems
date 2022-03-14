package gcyganek;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MessageWriter implements Runnable{

    private final Socket socket;
    private final Scanner systemIn;
    private final UdpChannel udpChannel;
    private final UdpChannel udpMulticastChannel;
    private PrintWriter out;
    private String username;

    public MessageWriter(Socket socket, UdpChannel udpChannel, UdpChannel udpMulticastChannel) throws IOException {
        this.socket = socket;
        this.systemIn = new Scanner(System.in);
        this.udpChannel = udpChannel;
        this.udpMulticastChannel = udpMulticastChannel;
        initializeOutputAndSendUsername();
    }

    private void initializeOutputAndSendUsername() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Enter your username:");
        String username = systemIn.nextLine();
        this.username = username;
        udpMulticastChannel.setUsername(username);
        out.println(username);
    }

    @Override
    public void run() {
        while (socket.isConnected() && !socket.isClosed()) {
            String msg = systemIn.nextLine();

            if (msg.startsWith("U ")) {
                udpChannel.sendUdpMessage(msg.substring(2));
            } else if (msg.startsWith("M ")) {
                udpMulticastChannel.sendUdpMessage(username + ": " + msg.substring(2));
            } else {
                out.println(msg);
            }
        }
        closeWriter();
    }

    public void closeWriter() {
        try {
            if (out != null) out.close();
            if (!socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
