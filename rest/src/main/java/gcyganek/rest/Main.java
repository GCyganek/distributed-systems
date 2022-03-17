package gcyganek.rest;

import gcyganek.rest.util.ApiKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(ApiKeys.class);

    public static void main(String[] args) {

        try {
            ApiKeys.loadApiKeys();
        } catch (Exception e) {
            logger.error("Reading api keys from " + ApiKeys.API_KEYS_PROPERTIES_FILE_NAME + " failed, closing the server: "
                    + e.getMessage());
        }

        SpringApplication.run(Main.class, args);
    }
}
