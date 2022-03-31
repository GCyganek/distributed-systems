package gcyganek.app.supplier;

import com.rabbitmq.client.*;
import gcyganek.app.ExchangeNames;
import gcyganek.app.ExchangesInitializer;
import gcyganek.app.QueueInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Supplier {

    private final Logger logger = LogManager.getLogger(Supplier.class);

    private final String host;
    private final String supplierName;
    private final List<String> itemsOnSale;
    private int ackIndex = 0;

    private Channel channel;

    public Supplier(String host, String supplierName, List<String> itemsOnSale) {
        this.host = host;
        this.supplierName = supplierName;
        this.itemsOnSale = itemsOnSale;
    }

    public void init() throws IOException, TimeoutException {
        this.channel = createChannel();
        initExchanges();
        initQueues();
        initMessageHandler();
    }

    private Channel createChannel() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);

        Connection connection = connectionFactory.newConnection();
        return connection.createChannel();
    }

    private void initExchanges() throws IOException {
        ExchangesInitializer exchangesInitializer = new ExchangesInitializer(channel);
        exchangesInitializer.initTopicExchanges();
    }

    private void initQueues() throws IOException {
        QueueInitializer queueInitializer = new QueueInitializer();
        initAdminQueue(queueInitializer);
        initItemsQueues(queueInitializer);
    }

    private void initAdminQueue(QueueInitializer queueInitializer) throws IOException {
        queueInitializer.initAdminQueue(channel, supplierName, true);
    }

    private void initItemsQueues(QueueInitializer queueInitializer) throws IOException {
        for (var item : itemsOnSale) {
            queueInitializer.initOrdersQueue(channel, item);
        }
    }

    private void initMessageHandler() throws IOException {
        setupAdminMessagesHandling(supplierName);
        setupOrdersMessagesHandling();
    }

    private void setupAdminMessagesHandling(String queueName) throws IOException {
        Consumer adminMessageConsumer = getAdminMessageConsumer();
        channel.basicConsume(queueName, false, adminMessageConsumer);
    }

    private void setupOrdersMessagesHandling() throws IOException {
        for (var item : itemsOnSale) {
            Consumer orderMessageConsumer = getOrderMessageConsumer(item);
            channel.basicConsume(item, false, orderMessageConsumer);
        }
    }

    private Consumer getAdminMessageConsumer() {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);

                logger.info("Supplier " + supplierName + " received message from Admin: " + message);

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    private Consumer getOrderMessageConsumer(String item) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String groupName = new String(body, StandardCharsets.UTF_8);

                logger.info("Supplier " + supplierName + " received from group " + groupName + " for " + item);

                replyOrderAck(groupName);

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    private void replyOrderAck(String groupName) throws IOException {
        String routingKey = groupName + "." + ackIndex++;
        channel.basicPublish(ExchangeNames.ORDERS_ACK_EXCHANGE, routingKey, null, supplierName.getBytes(StandardCharsets.UTF_8));
        logger.info("Sent ack with index " + ackIndex + " to group " + groupName);
    }

}
