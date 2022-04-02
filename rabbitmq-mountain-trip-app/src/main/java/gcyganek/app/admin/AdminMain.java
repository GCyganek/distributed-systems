package gcyganek.app.admin;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AdminMain {

    public static void main(String[] args) {

        Logger logger = setupLogger();

        String host = args[0];

        Admin admin = new Admin(host);
        try {
            admin.init();
        } catch (IOException | TimeoutException e) {
            logger.error(e.getMessage());
            return;
        }

        logger.info("Admin initialized. Enter 'exit' to close the client.");
        admin.run();
    }

    private static Logger setupLogger() {
        Configurator.setRootLevel(Level.TRACE);
        return LogManager.getLogger(AdminMain.class);
    }
}
