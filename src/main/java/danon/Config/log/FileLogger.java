package danon.Config.log;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends Logger {
    private FileWriter writer;

    @Override
    public void logString(String context) {
        initializeIfMissing();
        logMessage(context);
    }

    private void logMessage(String context) {
        try {
            writer.write(context);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Could not log message", e);
        }
    }

    private void initializeIfMissing() {
        if (writer == null) {
            try {
                writer = new FileWriter("debug.log", true);
            } catch (IOException e) {
                throw new RuntimeException("Couldn't open log file", e);
            }
        }
    }
}
