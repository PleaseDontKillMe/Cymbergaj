package danon.Cymbergaj.Config;

public class RuntimeConfig {
    private final String username;
    private final boolean isNetwork;
    private final String host;
    private final boolean isClient;

    public RuntimeConfig(String username, boolean isNetwork, String host, boolean isClient) {
        this.username = username;
        this.isNetwork = isNetwork;
        this.host = host;
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

    public boolean isNetwork() {
        return isNetwork;
    }
}
