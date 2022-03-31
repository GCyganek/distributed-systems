package gcyganek.app;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class ExchangesInitializer {

    private final Channel channel;

    public ExchangesInitializer(Channel channel) {
        this.channel = channel;
    }

    public void initTopicExchanges() throws IOException {
        for (String exchangeName : ExchangeNames.getExchangeNames()) {
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
        }
    }

    public void initExchange(String exchangeName, BuiltinExchangeType exchangeType) throws IOException {
        channel.exchangeDeclare(exchangeName, exchangeType);
    }
}
