package danon.Config;

import java.io.*;
import java.util.Properties;

class ConfigurationProperties {
    private final String filename = "config.properties";
    private final Properties properties = new Properties();

    ConfigurationProperties() {
        initialize();
    }

    private void initialize() {
        try {
            try {
                InputStream input = new FileInputStream(filename);
                properties.load(input);
            } catch (FileNotFoundException e) {
                createPropertiesFileAndLoad();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createPropertiesFileAndLoad() throws IOException {
        OutputStream output = new FileOutputStream(filename);

        properties.setProperty("database", "localhost");
        properties.setProperty("dbuser", "mkyong");
        properties.setProperty("dbpassword", "password");

        properties.store(output, null);
    }

    String getValue(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
