package gcyganek;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class UdpChannel implements Runnable {

    private final DatagramSocket datagramSocket;
    private final Socket serverSocket;
    private final Client client;

    public UdpChannel(Socket serverSocket, Client client) throws IOException {
        this.serverSocket = serverSocket;
        this.datagramSocket = new DatagramSocket(serverSocket.getLocalPort());
        this.client = client;
    }

    @Override
    public void run() {
        try {
            byte[] receiveBuffer = new byte[1024];

            while (!datagramSocket.isClosed()) {
                Arrays.fill(receiveBuffer, (byte)0);
                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                datagramSocket.receive(receivedPacket);
                String receivedMsg = new String(receivedPacket.getData());
                System.out.println(receivedMsg);
            }
        } catch (IOException e) {
            client.closeApp("Connection to the server has been lost. Closing the app. Press ENTER to end the process");
        }
    }

    public void closeDatagramSocket() {
        if (!datagramSocket.isClosed()) {
            datagramSocket.close();
        }
    }

    public void sendUdpMessage(String msg) {
        byte[] sendBuffer = msg.getBytes();

        InetAddress serverAddress = serverSocket.getInetAddress();
        int serverPort = serverSocket.getPort();

        try {
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
                    serverAddress, serverPort);

            datagramSocket.send(sendPacket);
        } catch (IOException e) {
            closeDatagramSocket();
        }
    }

}
