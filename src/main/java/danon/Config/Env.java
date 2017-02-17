package danon.Config;

import java.util.HashMap;
import java.util.Map;

public class Env {
    private final static ConfigurationProperties properties;

    static {
        properties = new ConfigurationProperties();
    }

    public static Logger getLogger() {
        String logger = properties.getValue("logger");
        return getLoggerByName(logger);
    }

    private static Logger getLoggerByName(String logger) {
        Map<String, Logger> loggers = new HashMap<>();
        loggers.put("stdOut", new StdOutLogger());
        loggers.put("file", new FileLogger());
        return loggers.get(logger);
    }
}
