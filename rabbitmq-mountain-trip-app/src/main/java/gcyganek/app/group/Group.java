package gcyganek.app.group;

import com.rabbitmq.client.Consumer;
import gcyganek.app.AdminMessageConsumer;
import gcyganek.app.ExchangeNames;
import gcyganek.app.QueueInitializer;
import gcyganek.app.RabbitMqClient;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Group extends RabbitMqClient {

    private final String adminQueueName;
    private final String ackQueueName;

    public Group(String host, String groupName) {
        super(host, groupName, LogManager.getLogger(Group.class));
        this.adminQueueName = groupName + "admin";
        this.ackQueueName = groupName;
    }

    @Override
    protected void inputHandler(String input) throws IOException {
        String message = clientName + " " + input;
        channel.basicPublish(ExchangeNames.ORDERS_EXCHANGE, input, null, message.getBytes(StandardCharsets.UTF_8));
        logger.info("Sent order for " + input);
    }

    @Override
    protected void initQueues() throws IOException {
        GroupQueueInitializer queueInitializer = new GroupQueueInitializer();
        initAdminQueue(queueInitializer);
        initGroupQueue(queueInitializer);
    }

    private void initAdminQueue(QueueInitializer queueInitializer) throws IOException {
        queueInitializer.initAdminQueue(channel, adminQueueName, false);
    }

    private void initGroupQueue(GroupQueueInitializer queueInitializer) throws IOException {
        queueInitializer.initOrdersAckQueue(channel, ackQueueName);
    }

    @Override
    protected void initMessageHandler() throws IOException {
        setupAdminMessageHandling();
        setupOrderAckMessagesConsumer();
    }

    private void setupAdminMessageHandling() throws IOException {
        String message = "Group " + clientName + " received message from Admin: ";
        Consumer consumer = new AdminMessageConsumer(channel, logger, message).getAdminMessageConsumer();
        channel.basicConsume(adminQueueName, false, consumer);
    }

    private void setupOrderAckMessagesConsumer() throws IOException {
        Consumer consumer = new OrderAckMessageConsumer(channel, logger, clientName).getMessageConsumer();
        channel.basicConsume(ackQueueName, false, consumer);
    }
}
