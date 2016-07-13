package danon.Cymbergaj.Model;

public class QuiteGamePointsCounter extends GamePointsCounter {

    public QuiteGamePointsCounter(Game game) {
        super(game, null);
    }

    @Override
    protected void playBellSound() {
    }
}
