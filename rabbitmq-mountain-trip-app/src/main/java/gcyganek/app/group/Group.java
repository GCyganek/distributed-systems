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

    public Group(String host, String groupName) {
        super(host, groupName, LogManager.getLogger(Group.class));
    }

    @Override
    protected void inputHandler(String input) throws IOException {
        String message = clientName + " " + input;
        channel.basicPublish(ExchangeNames.ORDERS_EXCHANGE, input, null, message.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void initQueues() throws IOException {
        GroupQueueInitializer queueInitializer = new GroupQueueInitializer();
        initAdminQueue(queueInitializer);
        initGroupQueue(queueInitializer);
    }

    private void initAdminQueue(QueueInitializer queueInitializer) throws IOException {
        queueInitializer.initAdminQueue(channel, clientName, false);
    }

    private void initGroupQueue(GroupQueueInitializer queueInitializer) throws IOException {
        queueInitializer.initOrdersAckQueue(channel, clientName);
    }

    @Override
    protected void initMessageHandler() throws IOException {
        setupAdminMessageHandling(clientName);
        setupOrderAckMessagesConsumer();
    }

    private void setupAdminMessageHandling(String queueName) throws IOException {
        String message = "Group " + clientName + " received message from Admin: ";
        Consumer consumer = new AdminMessageConsumer(channel, logger, message).getAdminMessageConsumer();
        channel.basicConsume(queueName, false, consumer);
    }

    private void setupOrderAckMessagesConsumer() throws IOException {
        Consumer consumer = new OrderAckMessageConsumer(channel, logger, clientName).getMessageConsumer();
        channel.basicConsume(clientName, false, consumer);
    }
}
