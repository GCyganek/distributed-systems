package gcyganek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final Logger logger = LogManager.getLogger(Server.class);
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);

            if (port == 0) {
                port = serverSocket.getLocalPort();
            }

            UdpChannel udpChannel = new UdpChannel(port);
            new Thread(udpChannel).start();

            logger.info("Server running on port " + port);

            while (!serverSocket.isClosed()) {

                Socket clientSocket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(clientSocket);

                logger.info("New client connected from address: " + clientSocket.getInetAddress().getHostAddress() +
                        ":" + clientSocket.getPort() + " with a username " + clientHandler.getClientUsername());

                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            closeServerSocket(serverSocket);
        }
    }

    private void closeServerSocket(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

}