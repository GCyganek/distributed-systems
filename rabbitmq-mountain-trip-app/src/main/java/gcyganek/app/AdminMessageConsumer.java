package gcyganek.app;

import com.rabbitmq.client.*;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AdminMessageConsumer {

    private final Channel channel;
    private final Logger logger;
    private final String loggerMessage;
    private final Consumer consumer;

    public AdminMessageConsumer(Channel channel, Logger logger, String loggerMessage) {
        this.channel = channel;
        this.logger = logger;
        this.loggerMessage = loggerMessage;
        this.consumer = createAdminMessageConsumer();
    }

    private Consumer createAdminMessageConsumer() {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);

                logger.info(loggerMessage + message);

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    public Consumer getAdminMessageConsumer() {
        return consumer;
    }
}
