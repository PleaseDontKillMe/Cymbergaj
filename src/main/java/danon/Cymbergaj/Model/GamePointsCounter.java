package danon.Cymbergaj.Model;

import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;

import java.util.Objects;

public class GamePointsCounter extends ContactAdapter {
    private final Game game;
    private final SoundsRepository soundsRepository;

    public GamePointsCounter(Game game, SoundsRepository soundsRepository) {
        this.game = game;
        this.soundsRepository = soundsRepository;
    }

    @Override
    public void end(ContactPoint point) {
        String user1 = (String) point.getBody1().getUserData();
        String user2 = (String) point.getBody2().getUserData();

        if (oneOfBodiesIsBall(user1, user2)) {
            performPointsChecking(user1, user2);
        }

        super.end(point);
    }

    private boolean oneOfBodiesIsBall(String user1, String user2) {
        return user1 != null && user2 != null && (user1.equals("ball") || user2.equals("ball"));
    }

    private void performPointsChecking(String user1, String user2) {
        if (Objects.equals(user1, "left") || Objects.equals(user2, "left")) { // Left gate was hit
            game.pointForRight();
            playBellSound();
        }

        if (Objects.equals(user1, "right") || Objects.equals(user2, "right")) { // Right gate was hit
            game.pointForLeft();
            playBellSound();
        }
    }

    private void playBellSound() {
        soundsRepository.bell.setFramePosition(0);
        soundsRepository.bell.start();
    }
}
