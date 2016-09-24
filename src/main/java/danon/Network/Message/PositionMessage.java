package danon.Network.Message;

public class PositionMessage implements Message {

    private final char player;
    private final double positionX;
    private final double positionY;

    public PositionMessage(char player, double positionX, double positionY) {
        this.player = player;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public char getPlayer() {
        return player;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }
}
