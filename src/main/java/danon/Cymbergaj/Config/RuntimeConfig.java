package danon.Cymbergaj.Config;

public class RuntimeConfig {
    private final String username;
    private final boolean isNetwork;
    private final String host;

    RuntimeConfig(String username, boolean isNetwork, String host) {
        this.username = username;
        this.isNetwork = isNetwork;
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public boolean isNetwork() {
        return isNetwork;
    }
}
