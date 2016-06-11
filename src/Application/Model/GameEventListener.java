package Application.Model;


public interface GameEventListener {

    default void render() { }
    default void update(){ }
    default void fps(double fps) { }
    default void gameEnd() { }

}
