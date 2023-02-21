import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Pronunciation {
    Clip soundClip;
    AudioInputStream soundStream;

    public void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName);
            soundStream = AudioSystem.getAudioInputStream(file);
            soundClip = AudioSystem.getClip();
            soundClip.open(soundStream);
        } catch (Exception e) {
            System.out.println("No music today.");
        }
    }

    public void play() {
        soundClip.start();
    }
}
