package danon.Network;

public class IntroduceMessage implements Message {
    private final String name;

    public IntroduceMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
