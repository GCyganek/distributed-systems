package gcyganek.app.supplier;

import com.rabbitmq.client.Channel;
import gcyganek.app.ExchangeNames;
import gcyganek.app.QueueInitializer;

import java.io.IOException;

public class SupplierQueueInitializer extends QueueInitializer {

    public SupplierQueueInitializer() { }

    public void initOrdersQueue(Channel channel, String queueName) throws IOException {
        super.initQueue(channel, queueName, queueName, ExchangeNames.ORDERS_EXCHANGE);
    }
}
