package gcyganek.app.admin;

import com.rabbitmq.client.Channel;
import gcyganek.app.MessageConsumer;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AdminDuplicatesConsumer extends MessageConsumer {

    public AdminDuplicatesConsumer(Channel channel, Logger logger, String clientName) {
        super(channel, logger, clientName);
    }

    @Override
    protected void handleDeliveryInterior(String message) throws IOException {
        logger.info("New message intercepted: " + message);
    }
}
