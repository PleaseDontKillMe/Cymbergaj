package danon.Config;

import java.io.*;
import java.util.Properties;

public class ConfigurationProperties {
    private final String filename = ".config.properties";
    private final Properties properties = new Properties();

    public ConfigurationProperties() {
        initialize();
    }

    private void initialize() {
        try {
            InputStream input = ConfigurationProperties.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                createPropertiesFile();
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createPropertiesFile() throws IOException {
        OutputStream output = new FileOutputStream("config.properties");

        properties.setProperty("database", "localhost");
        properties.setProperty("dbuser", "mkyong");
        properties.setProperty("dbpassword", "password");

        properties.store(output, null);
    }

    public String getValue(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
