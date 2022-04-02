package gcyganek.app;

import com.rabbitmq.client.Channel;

import java.io.IOException;

public class QueueInitializer {

    public void initQueue(Channel channel, String queueName, String routingKey, String exchangeName) throws IOException
    {
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
    }

    public void initAdminQueue(Channel channel, String queueName, boolean forSupplier) throws IOException {
        String routingKey = forSupplier ? "*.suppliers" : "groups.*";
        initQueue(channel, queueName, routingKey, ExchangeNames.ADMIN_EXCHANGE);
    }

}
