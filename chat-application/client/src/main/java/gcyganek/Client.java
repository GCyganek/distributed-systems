package gcyganek;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;


public class Client {

    private final String hostName;
    private final int port;
    private final InetAddress multicastAddress;
    private final int multicastPort;
    private UdpChannel udpChannel;
    private UdpChannel udpMulticastChannel;
    private MessageReader messageReader;
    private MessageWriter messageWriter;
    private boolean appClosedAlready = false;

    public Client(String hostName, int port, InetAddress multicastAddress, int multicastPort) {
        this.hostName = hostName;
        this.port = port;
        this.multicastAddress = multicastAddress;
        this.multicastPort = multicastPort;
    }

    public void run() {
        Socket socket = null;
        try {
            MulticastSocket multicastSocket = new MulticastSocket(multicastPort);
            multicastSocket.joinGroup(new InetSocketAddress(multicastAddress, multicastPort),
                    NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));

            socket = new Socket(hostName, port);

            udpChannel = new UdpChannel(new DatagramSocket(socket.getLocalPort()), socket.getPort(), socket.getInetAddress(), this);
            udpMulticastChannel = new UdpChannel(multicastSocket, multicastPort, multicastAddress,this);
            messageReader = new MessageReader(socket, this);
            messageWriter = new MessageWriter(socket, udpChannel, udpMulticastChannel);

            Thread messageReaderThread = new Thread(messageReader);
            Thread messageWriterThread = new Thread(messageWriter);
            Thread udpChannelThread = new Thread(udpChannel);
            Thread udpMulticastChannelThread = new Thread(udpMulticastChannel);

            messageReaderThread.start();
            udpChannelThread.start();
            udpMulticastChannelThread.start();
            messageWriterThread.start();

            System.out.println("Connected to the server, you can start chatting!");

            messageReaderThread.join();
            udpChannelThread.join();
            udpMulticastChannelThread.join();
            messageWriterThread.join();

        } catch (IOException | InterruptedException e) {
            closeApp("An unexpected error has happened. Closing the app");
        } finally {
            closeSocket(socket);
        }
    }

    public synchronized void closeApp(String endMsg) {
        if (appClosedAlready) return;
        appClosedAlready = true;

        if (endMsg != null) {
            System.err.println(endMsg);
        }
        if (messageReader != null) messageReader.closeReader();
        if (udpChannel != null) udpChannel.closeDatagramSocket();
        if (udpMulticastChannel != null) udpMulticastChannel.closeDatagramSocket();
        if (messageWriter != null) messageWriter.closeWriter();
    }

    private void closeSocket(Socket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                System.out.println(networkInterface);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
