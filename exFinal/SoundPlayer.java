package exFinal;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
    public void playSound(String soundFile) {
        try {
            // Open an audio input stream.
            File soundPath = new File(soundFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundPath);
            
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
            
            // Play the audio clip.
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }
}
