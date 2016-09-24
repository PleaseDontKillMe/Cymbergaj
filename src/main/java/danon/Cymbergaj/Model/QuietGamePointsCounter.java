package danon.Cymbergaj.Model;

public class QuietGamePointsCounter extends GamePointsCounter {

    public QuietGamePointsCounter(Game game) {
        super(game, null);
    }

    @Override
    protected void playBellSound() {
    }
}
