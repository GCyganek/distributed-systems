package gcyganek.rest.util;

import java.io.*;
import java.util.Properties;

public final class ApiKeys {

    private static final String OMDb_API_KEY_FIELD = "omdb_api_key";
    private static final String TMDB_API_KEY_FIELD = "tmdb_api_key";

    public static final String API_KEYS_PROPERTIES_FILE_NAME = "api_keys.properties";

    public static String OMDb_API_KEY;
    public static String TMDB_API_KEY;

    private ApiKeys() { }

    public static void loadApiKeys() throws IOException {
        InputStream input = new FileInputStream(API_KEYS_PROPERTIES_FILE_NAME);

        Properties prop = new Properties();
        prop.load(input);

        TMDB_API_KEY = prop.getProperty(TMDB_API_KEY_FIELD);
        OMDb_API_KEY = prop.getProperty(OMDb_API_KEY_FIELD);

        input.close();
    }

}
