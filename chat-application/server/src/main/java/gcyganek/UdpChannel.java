package gcyganek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class UdpChannel implements Runnable {

    private final Logger logger = LogManager.getLogger(UdpChannel.class);
    private final DatagramSocket datagramSocket;

    public UdpChannel(int port) throws SocketException {
        this.datagramSocket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        try {
            byte[] receiveBuffer = new byte[1024];

            while (!datagramSocket.isClosed()) {
                Arrays.fill(receiveBuffer, (byte)0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                datagramSocket.receive(receivePacket);

                logger.info("Broadcasting UDP datagram received from: " + receivePacket.getAddress().getHostAddress()
                        + ":" + receivePacket.getPort());

                ClientHandler.broadcastUdpMessage(receivePacket, datagramSocket);
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            datagramSocket.close();
        }
    }
}
