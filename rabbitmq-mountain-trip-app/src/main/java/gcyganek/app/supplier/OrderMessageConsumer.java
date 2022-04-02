package gcyganek.app.supplier;

import com.rabbitmq.client.Channel;
import gcyganek.app.ExchangeNames;
import gcyganek.app.MessageConsumer;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class OrderMessageConsumer extends MessageConsumer {

    private final Supplier supplier;
    public OrderMessageConsumer(Channel channel, Logger logger, String supplierName, Supplier supplier) {
        super(channel, logger, supplierName);
        this.supplier = supplier;
    }

    @Override
    protected void handleDeliveryInterior(String message) throws IOException {
        String[] messageItems = message.split(" ");

        if (messageItems.length != 2) {
            logger.info("Received order was invalid: " + message);
            return;
        }

        String groupName = messageItems[0];
        String item = messageItems[1];

        logger.info("Supplier " + clientName + ": received order from group " + groupName + " for " + item);

        replyOrderAck(groupName, item);
    }

    private void replyOrderAck(String groupName, String item) throws IOException {
        int ackIndex = supplier.getAndIncrementAckIndex();
        String routingKey = groupName + "." + ackIndex;
        String message = clientName + " " + ackIndex + " " + item;
        channel.basicPublish(ExchangeNames.ORDERS_ACK_EXCHANGE, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
        logger.info("Sent ack with index " + ackIndex + " to group " + groupName);
    }
}
