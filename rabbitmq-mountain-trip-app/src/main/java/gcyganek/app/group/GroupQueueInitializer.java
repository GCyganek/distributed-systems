package gcyganek.app.group;

import com.rabbitmq.client.Channel;
import gcyganek.app.ExchangeNames;
import gcyganek.app.QueueInitializer;

import java.io.IOException;

public class GroupQueueInitializer extends QueueInitializer {

    public GroupQueueInitializer() {
    }

    public void initOrdersAckQueue(Channel channel, String groupName) throws IOException {
        initQueue(channel, groupName, groupName, ExchangeNames.ORDERS_ACK_EXCHANGE);
    }
}
