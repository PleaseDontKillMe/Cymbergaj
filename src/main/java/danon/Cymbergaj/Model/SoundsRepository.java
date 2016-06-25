package danon.Cymbergaj.Model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundsRepository {

    public Clip lookAtMyHorse;
    public Clip bell;

    public void load() {
        lookAtMyHorse = loadClip("res/LookAtMyHorse.wav");
        bell = loadClip("res/bell.wav");
    }

    public void addStopListener(Clip clip, Runnable onStop) {
        clip.addLineListener(event -> {
            if (event.getType().toString().equals("Stop")) {
                onStop.run();
            }
        });
    }

    public void play(Clip clip) {
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
