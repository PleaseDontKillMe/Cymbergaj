package danon.Cymbergaj.Model;

public class QuiteGamePointsCounter extends GamePointsCounter {

    public QuiteGamePointsCounter(Game game, SoundsRepository soundsRepository) {
        super(game, soundsRepository);
    }

    @Override
    protected void playBellSound() {
    }
}
