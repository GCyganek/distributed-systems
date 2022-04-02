package gcyganek.app.supplier;

import com.rabbitmq.client.*;
import gcyganek.app.AdminMessageConsumer;
import gcyganek.app.ExchangesInitializer;
import gcyganek.app.QueueInitializer;
import gcyganek.app.RabbitMqClient;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.List;

public class Supplier extends RabbitMqClient {

    private final List<String> itemsOnSale;
    private int ackIndex = 0;

    public Supplier(String host, String supplierName, List<String> itemsOnSale) {
        super(host, supplierName, LogManager.getLogger(Supplier.class));
        this.itemsOnSale = itemsOnSale;
    }

    @Override
    protected void inputHandler(String input) {
    }

    @Override
    protected void initQueues() throws IOException {
        SupplierQueueInitializer queueInitializer = new SupplierQueueInitializer();
        initAdminQueue(queueInitializer);
        initItemsQueues(queueInitializer);
    }

    private void initAdminQueue(QueueInitializer queueInitializer) throws IOException {
        queueInitializer.initAdminQueue(channel, clientName, true);
    }

    private void initItemsQueues(SupplierQueueInitializer queueInitializer) throws IOException {
        for (var item : itemsOnSale) {
            queueInitializer.initOrdersQueue(channel, item);
        }
    }

    @Override
    protected void initMessageHandler() throws IOException {
        setupAdminMessagesHandling(clientName);
        setupOrdersMessagesHandling();
    }

    private void setupAdminMessagesHandling(String queueName) throws IOException {
        String message = "Supplier " + clientName + " received message from Admin: ";
        Consumer consumer = new AdminMessageConsumer(channel, logger, message).getAdminMessageConsumer();
        channel.basicConsume(queueName, false, consumer);
    }

    private void setupOrdersMessagesHandling() throws IOException {
        for (var item : itemsOnSale) {
            Consumer orderMessageConsumer = new OrderMessageConsumer(channel, logger, clientName, ackIndex++)
                    .getMessageConsumer();

            channel.basicConsume(item, false, orderMessageConsumer);
        }
    }
}
