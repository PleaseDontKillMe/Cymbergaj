package danon.Cymbergaj.Config;

public class RuntimeConfig {
    private final String host;
    private final String username;
    private final boolean isClient;

    public RuntimeConfig(String host, String username, boolean isClient) {
        this.host = host;
        this.username = username;
        this.isClient = isClient;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public boolean isClient() {
        return isClient;
    }
}
