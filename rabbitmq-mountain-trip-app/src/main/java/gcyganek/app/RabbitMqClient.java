package gcyganek.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public abstract class RabbitMqClient {

    protected final String host;
    protected final String clientName;
    protected final Logger logger;

    protected Connection connection;
    protected Channel channel;

    protected RabbitMqClient(String host, String clientName, Logger logger) {
        this.host = host;
        this.clientName = clientName;
        this.logger = logger;
    }

    public void init() throws IOException, TimeoutException {
        this.channel = createChannel();
        initExchanges();
        initQueues();
        initMessageHandler();
    }

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String input = br.readLine();

                if (Objects.equals(input, "exit")) {
                    logger.info("Closing the client");
                    break;
                }

                inputHandler(input);
            }
            catch (IOException e) {
                logger.warn("There was an error while trying to read the input, try again! Error: " + e.getMessage());
            }
        }

        closeClient();
    }

    protected abstract void inputHandler(String input) throws IOException;

    private Channel createChannel() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);

        connection = connectionFactory.newConnection();
        return connection.createChannel();
    }

    protected void closeClient() {
        try {
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            logger.error("Error while closing the client: " + e.getMessage());
        }
    }

    protected void initExchanges() throws IOException {
        ExchangesInitializer exchangesInitializer = new ExchangesInitializer(channel);
        exchangesInitializer.initTopicExchanges();
    }

    protected abstract void initQueues() throws IOException;
    protected abstract void initMessageHandler() throws IOException;
}
