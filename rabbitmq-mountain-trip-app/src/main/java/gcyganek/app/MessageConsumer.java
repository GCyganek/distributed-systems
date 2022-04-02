package gcyganek.app;

import com.rabbitmq.client.*;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class MessageConsumer {

    protected final Channel channel;
    protected final Logger logger;
    protected final String clientName;

    protected MessageConsumer(Channel channel, Logger logger, String clientName) {
        this.channel = channel;
        this.logger = logger;
        this.clientName = clientName;
    }

    public Consumer getMessageConsumer() {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);

                handleDeliveryInterior(message);

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    protected abstract void handleDeliveryInterior(String message) throws IOException;
}
