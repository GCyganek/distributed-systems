package gcyganek;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class AppMain {

    public static void main(String[] args) {
        Configurator.setRootLevel(Level.TRACE);
        Logger logger = LogManager.getLogger(AppMain.class);

        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            logger.warn("Invalid port number, setting default port number...");
            port = 0;
        }

        Server server = new Server(port);
        server.run();
    }
}
