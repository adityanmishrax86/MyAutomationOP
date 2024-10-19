package Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configurator {

    static Properties properties;

    protected static final String CONFIG_FILE_PATH = "src/test/resources/config/config.properties";

    public static void readConfigProperties() throws IOException {
        FileReader reader = new FileReader(CONFIG_FILE_PATH);
        properties = new Properties();
        properties.load(reader);
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
