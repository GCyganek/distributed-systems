package gcyganek;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UdpChannel implements Runnable {

    private final DatagramSocket datagramSocket;
    private final int sendPort;
    private final InetAddress sendAddress;
    private final Client client;

    public UdpChannel(DatagramSocket datagramSocket, int sendPort, InetAddress sendAddress, Client client) throws IOException {
        this.datagramSocket = datagramSocket;
        this.sendPort = sendPort;
        this.sendAddress = sendAddress;
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

        try {
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
                    sendAddress, sendPort);

            datagramSocket.send(sendPacket);
        } catch (IOException e) {
            closeDatagramSocket();
        }
    }

}
