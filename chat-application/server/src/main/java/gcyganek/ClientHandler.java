package gcyganek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {

    private static final List<ClientHandler> clientHandlers = new ArrayList<>();

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

    @Override
    public void run() {
        try {
            String msg;
            while ((msg = in.readLine()) != null) {
                logger.info("Broadcasting received message from client " + clientUsername + ": " + msg);
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

    private void addClientHandler() {
        clientHandlers.add(this);
        broadcastMessage(clientUsername + " has joined the chat!");
    }

    private void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage(clientUsername + " has left the chat");
    }

    public String getClientUsername() {
        return clientUsername;
    }
}
