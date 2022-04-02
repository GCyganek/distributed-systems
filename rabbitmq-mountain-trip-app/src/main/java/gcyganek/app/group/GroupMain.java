package gcyganek.app.group;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class GroupMain {

    public static void main(String[] args) {

        Logger logger = setupLogger();

        String host = args[0];
        String groupName = args[1];

        Group group = new Group(host, groupName);
        try {
            group.init();
        } catch (IOException | TimeoutException e) {
            logger.error(e.getMessage());
        }

        logger.info("Group initialized. Enter items to order od 'exit' to close the client.");
        group.run();
    }

    private static Logger setupLogger() {
        Configurator.setRootLevel(Level.TRACE);
        return LogManager.getLogger(GroupMain.class);
    }
}
