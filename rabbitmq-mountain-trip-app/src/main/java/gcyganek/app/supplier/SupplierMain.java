package gcyganek.app.supplier;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class SupplierMain {

    public static void main(String[] args) {

        Logger logger = setupLogger();

        String host = args[0];
        String supplierName = args[1];
        List<String> itemsOnSale = Arrays.asList(args).subList(2, args.length);

        Supplier supplier = new Supplier(host, supplierName, itemsOnSale);
        try {
            supplier.init();
        } catch (IOException | TimeoutException e) {
            logger.error(e.getMessage());
        }

        logger.info("Supplier initialized. Enter 'exit' to close the client.");
        supplier.run();
    }

    private static Logger setupLogger() {
        Configurator.setRootLevel(Level.TRACE);
        return LogManager.getLogger(SupplierMain.class);
    }
}
