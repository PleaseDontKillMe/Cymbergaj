package danon.Cymbergaj.Model;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundsRepository {

    public Clip lookAtMyHorse;
    public Clip bell;

    public void load() {
        lookAtMyHorse = loadClip("LookAtMyHorse.wav");
        bell = loadClip("bell.wav");
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
        } catch (LineUnavailableException e) {
            throw new RuntimeException("Line Couldn't load file '" + filename + "'" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("IO Couldn't load file '" + filename + "'" + e.getMessage());
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("Unsup Couldn't load file '" + filename + "'" + e.getMessage());
        }
    }

    private Clip tryLoadClip(String filename) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        Clip clip = AudioSystem.getClip();
        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
        clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(is)));
        return clip;
    }
}
