package gcyganek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class ClientHandler implements Runnable {

    private static final List<ClientHandler> clientHandlers = new ArrayList<>();
    private static final Map<AddressAndPort, String> addressAndPortToUsername = new HashMap<>();

    private final Logger logger = LogManager.getLogger(ClientHandler.class);
    private final Socket clientSocket;
    private String clientUsername;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        initializeClientHandlerInputOutput();
        addClientHandler();
    }

    private void initializeClientHandlerInputOutput() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            clientUsername = in.readLine();
        } catch (IOException e) {
            logger.error(e.getMessage());
            closeClientHandler();
        }
    }

    private void addClientHandler() {
        clientHandlers.add(this);
        addressAndPortToUsername.put(new AddressAndPort(clientSocket.getInetAddress(),
                clientSocket.getPort()), clientUsername);

        broadcastMessage(clientUsername + " has joined the chat!");
    }

    @Override
    public void run() {
        try {
            String msg;
            while ((msg = in.readLine()) != null) {
                logger.info("Broadcasting received message from client " + clientUsername);
                broadcastMessage(clientUsername + ": " + msg);
            }
        } catch (IOException e) {
            logger.info("Closing client " + clientUsername);
            closeClientHandler();
        }
    }

    private void broadcastMessage(String msg) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler != this) {
                clientHandler.out.println(msg);
            }
        }
    }

    private void closeClientHandler() {
        removeClientHandler();
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
        }
        catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void removeClientHandler() {
        clientHandlers.remove(this);
        addressAndPortToUsername.remove(new AddressAndPort(clientSocket.getInetAddress(), clientSocket.getPort()));
        broadcastMessage(clientUsername + " has left the chat");
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public static void broadcastUdpMessage(DatagramPacket receivePacket, DatagramSocket datagramSocket) {
        Logger logger = LogManager.getLogger(ClientHandler.class);

        InetAddress senderAddress = receivePacket.getAddress();
        int senderPort = receivePacket.getPort();

        String senderUsername = addressAndPortToUsername.get(new AddressAndPort(senderAddress, senderPort));

        byte[] msg = (senderUsername + ": " + new String(receivePacket.getData())).getBytes();

        for (ClientHandler clientHandler : clientHandlers) {
            InetAddress clientAddress = clientHandler.clientSocket.getInetAddress();
            int clientPort = clientHandler.clientSocket.getPort();

            if(!Objects.equals(senderUsername, clientHandler.clientUsername)) {
                try {
                    DatagramPacket sendPacket = new DatagramPacket(msg, msg.length, clientAddress, clientPort);
                    datagramSocket.send(sendPacket);
                } catch (IOException e) {
                    logger.warn(e.getMessage());
                }
            }
        }
    }

}
