package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();
    private static final Logger log = LogManager.getLogger(ConfigReader.class);

    static {
        String env = System.getProperty("env", "qa1");
        String path = "src/test/resources/config/" + env + ".properties";

        log.debug("Loading config from: {}", path);

        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
            log.info("Config loaded successfully for env: {}", env);
        } catch (IOException e) {
            log.error("Can`t load config: {}", path);
            throw new RuntimeException("Cannot load config: " + path);
        }
    }

    public static String get(String key) {
        log.debug("Getting property: {}", key);
        return properties.getProperty(key);
    }
}