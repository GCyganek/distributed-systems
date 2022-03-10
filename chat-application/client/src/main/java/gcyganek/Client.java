package gcyganek;

import java.io.IOException;
import java.net.Socket;


public class Client {

    private final String hostName;
    private final int port;
    private UdpChannel udpChannel;
    private MessageReader messageReader;
    private MessageWriter messageWriter;
    private boolean appClosedAlready = false;

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void run() {
        Socket socket = null;
        try {
            socket = new Socket(hostName, port);
            udpChannel = new UdpChannel(socket, this);
            messageReader = new MessageReader(socket, this);
            messageWriter = new MessageWriter(socket, udpChannel);

            Thread messageReaderThread = new Thread(messageReader);
            Thread messageWriterThread = new Thread(messageWriter);
            Thread udpChannelThread = new Thread(udpChannel);

            messageReaderThread.start();
            udpChannelThread.start();
            messageWriterThread.start();

            System.out.println("Connected to the server, you can start chatting!");

            messageReaderThread.join();
            udpChannelThread.join();
            messageWriterThread.join();

        } catch (IOException | InterruptedException e) {
            closeApp("An unexpected error has happened. Closing the app. Press ENTER to end the process");
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
        if (messageWriter != null) messageWriter.closeWriter();
    }

    private void closeSocket(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
