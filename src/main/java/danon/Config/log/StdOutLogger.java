package danon.Config.log;

public class StdOutLogger extends Logger {
    @Override
    public void logString(String context) {
        System.out.printf("[%s]", context);
    }
}
