import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class audio {
    private Clip clip;

    public audio(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.stop();
    }

    public void startaudio() {
        clip.start();
    }
    public void stopaudio() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }
    public void closeaudio() {
        clip.close();
    }
}
