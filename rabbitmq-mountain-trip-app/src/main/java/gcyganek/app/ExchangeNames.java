package gcyganek.app;

import java.util.List;

public final class ExchangeNames {
    public static final String ORDERS_EXCHANGE = "orders";
    public static final String ORDERS_ACK_EXCHANGE = "orders-ack";
    public static final String ADMIN_EXCHANGE = "admin";

    private ExchangeNames() { }

    public static List<String> getExchangeNames() {
        return List.of(ORDERS_EXCHANGE, ORDERS_ACK_EXCHANGE, ADMIN_EXCHANGE);
    }
}
