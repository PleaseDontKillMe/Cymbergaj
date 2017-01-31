package danon.Cymbergaj.Config;

@FunctionalInterface
public interface GetConfigListener {
    void getConfig(StartupConfiguration config);
}
