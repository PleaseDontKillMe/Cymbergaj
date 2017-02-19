package danon.Config;

import danon.Config.log.FileLogger;
import danon.Config.log.Logger;
import danon.Config.log.StdOutLogger;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final static ConfigurationProperties properties = new ConfigurationProperties();

    public static Logger getLogger() {
        String logger = properties.getValue("logger");
        return getLoggerByName(logger);
    }

    private static Logger getLoggerByName(String logger) {
        Map<String, Logger> loggers = new HashMap<>();
        loggers.put("stdOut", new StdOutLogger());
        loggers.put("file", new FileLogger());
        if (loggers.containsKey(logger)) {
            return loggers.get(logger);
        }
        throw new RuntimeException("No logger specified");
    }
}
