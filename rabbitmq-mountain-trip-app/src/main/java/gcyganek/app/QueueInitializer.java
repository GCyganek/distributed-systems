package gcyganek.app;

import com.rabbitmq.client.Channel;

import java.io.IOException;

public class QueueInitializer {

    public QueueInitializer() {
    }

    private void initQueue(Channel channel, String queueName, String routingKey, String exchangeName) throws IOException
    {
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
    }

    public String initOrdersQueue(Channel channel, String queueName) throws IOException {
        initQueue(channel, queueName, queueName, ExchangeNames.ORDERS_EXCHANGE);
        return queueName;
    }

    public String initOrdersAckQueue(Channel channel, String groupName) throws IOException {
        String routingKey = groupName + ".*";
        initQueue(channel, groupName, routingKey, ExchangeNames.ORDERS_ACK_EXCHANGE);
        return groupName;
    }

    public String initAdminDuplicatesQueue(Channel channel, String queueName) throws IOException {
        String routingKey = "#";
        initQueue(channel, queueName, routingKey, ExchangeNames.ORDERS_EXCHANGE); // initialized and bound to orders exchange
        channel.queueBind(queueName, ExchangeNames.ORDERS_ACK_EXCHANGE, routingKey); // bound to orders ack exchange
        return queueName;
    }

    public String initAdminQueue(Channel channel, String queueName, boolean forSupplier) throws IOException {
        String routingKey = forSupplier ? "suppliers.*" : "*.groups";
        initQueue(channel, queueName, routingKey, ExchangeNames.ADMIN_EXCHANGE);
        return queueName;
    }

}
