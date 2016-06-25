package danon.Cymbergaj;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundsRepository {

    Clip lookAtMyHorse;
    Clip bell;

    void load() {
        lookAtMyHorse = loadClip("res/LookAtMyHorse.wav");
        bell = loadClip("res/bell.wav");
    }

    void addOnEndListener(Clip clip, Runnable onStop) {
        clip.addLineListener(event -> {
            if (event.getType().toString().equals("Stop")) {
                onStop.run();
            }
        });
    }

    void play(Clip clip) {
        clip.start();
    }

    private Clip loadClip(String filename) {
        try {
            return tryLoadClip(filename);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException("Couldn't load file '" + filename + "'");
        }
    }

    private Clip tryLoadClip(String filename) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
        return clip;
    }
}
