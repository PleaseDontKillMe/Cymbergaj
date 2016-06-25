package danon.Cymbergaj;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundsRepository {

    public Clip lookAtMyHorse;
    public Clip konRafal;
    public Clip bell;

    public void load() {
        lookAtMyHorse = loadClip("res/LookAtMyHorse.wav");
        konRafal = loadClip("res/KonRafal.wav");
        bell = loadClip("res/bell.wav");
    }

    private Clip loadClip(String filename) {
        try {
            File file = new File(filename);
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip.open(ais);
            return clip;
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException("Couldn't load file '" + filename + "'");
        }
    }


}
