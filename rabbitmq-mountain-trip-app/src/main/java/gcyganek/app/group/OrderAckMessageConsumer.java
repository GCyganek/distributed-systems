package gcyganek.app.group;

import com.rabbitmq.client.*;
import gcyganek.app.MessageConsumer;
import org.apache.logging.log4j.Logger;

public class OrderAckMessageConsumer extends MessageConsumer {

    public OrderAckMessageConsumer(Channel channel, Logger logger, String groupName) {
        super(channel, logger, groupName);
    }

    @Override
    protected void handleDeliveryInterior(String message) {
        String[] messageItems = message.split(" ");
        String supplierName = messageItems[0];
        String ackIndex = messageItems[1];
        String item = messageItems[2];

        logger.info("Group " + clientName + " received order ack from supplier " + supplierName + " with index "
                + ackIndex + " for item " + item);
    }
}
