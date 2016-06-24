package danon.Cymbergaj.Model;


public interface GameEventListener {

    default void render() {
    }

    default void update(double elapsedSeconds) {
    }

    default void gameEnd() {
    }
}
