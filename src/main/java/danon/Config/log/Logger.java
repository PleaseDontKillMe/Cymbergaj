package danon.Config.log;

import danon.Config.Environment;

public abstract class Logger {
    private static final Logger logger = Environment.getLogger();

    public static void log(String context) {
        logger.logString(context);
    }

    abstract void logString(String context);
}
