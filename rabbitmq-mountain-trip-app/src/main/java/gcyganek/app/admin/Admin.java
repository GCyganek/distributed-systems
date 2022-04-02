package gcyganek.app.admin;

import com.rabbitmq.client.Consumer;
import gcyganek.app.ExchangeNames;
import gcyganek.app.RabbitMqClient;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Admin extends RabbitMqClient {

    public Admin(String host) {
        super(host, "admin", LogManager.getLogger(Admin.class));
    }

    @Override
    protected void inputHandler(String input) throws IOException {
        String routingKey;
        String message;
        if (input.startsWith("suppliers")) {
            routingKey = "x.suppliers";
            message = input.replaceFirst("suppliers ", "");
        }
        else if (input.startsWith("groups")) {
            routingKey = "groups.x";
            message = input.replaceFirst("groups ", "");
        }
        else {
            routingKey = "groups.suppliers";
            message = input;
        }

        channel.basicPublish(ExchangeNames.ADMIN_EXCHANGE, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
        logger.info("Sent message with routing key " + routingKey + "\tMessage: " + message);
    }

    @Override
    protected void initQueues() throws IOException {
        AdminQueueInitializer queueInitializer = new AdminQueueInitializer();
        initAdminDuplcatesQueue(queueInitializer);
    }

    private void initAdminDuplcatesQueue(AdminQueueInitializer queueInitializer) throws IOException {
        queueInitializer.initAdminDuplicatesQueue(channel, clientName);
    }

    @Override
    protected void initMessageHandler() throws IOException {
        setupDuplicatesHandler();
    }

    private void setupDuplicatesHandler() throws IOException {
        Consumer consumer = new AdminDuplicatesConsumer(channel, logger, clientName).getMessageConsumer();
        channel.basicConsume(clientName, false, consumer);
    }
}
