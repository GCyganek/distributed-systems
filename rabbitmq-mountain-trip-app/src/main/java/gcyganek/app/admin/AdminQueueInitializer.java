package gcyganek.app.admin;

import com.rabbitmq.client.Channel;
import gcyganek.app.ExchangeNames;
import gcyganek.app.QueueInitializer;

import java.io.IOException;

public class AdminQueueInitializer extends QueueInitializer {

    public AdminQueueInitializer() { }

    public void initAdminDuplicatesQueue(Channel channel, String queueName) throws IOException {
        String routingKey = "#";
        initQueue(channel, queueName, routingKey, ExchangeNames.ORDERS_EXCHANGE); // initialized and bound to orders exchange
        channel.queueBind(queueName, ExchangeNames.ORDERS_ACK_EXCHANGE, routingKey); // bound to orders ack exchange
    }
}
