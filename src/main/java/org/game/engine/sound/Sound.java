package org.game.engine.sound;

import javax.sound.sampled.Clip;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.game.util.constants.AppContext;

public class Sound {
    private static Clip clipFlap;

    private Sound() {}

    public static void init() {
        try {
            File flap = new File(AppContext.FLAP_AUD);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(flap);

            clipFlap = AudioSystem.getClip();
            clipFlap.open(audioInputStream);

        } catch(IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
            clipFlap = null;
        }
    }

    public static void flap() {
        if (clipFlap == null) return;
        //if (clipFlap.isRunning()) clipFlap.stop();
        clipFlap.stop();
        clipFlap.setFramePosition(0);
        clipFlap.start();
    }

    public static void stopFlap() {
        if (clipFlap == null) return;
        
        clipFlap.stop();
        clipFlap.setFramePosition(0);
    }

    public static void close() {
        if (clipFlap != null) {
            clipFlap.close();
            clipFlap = null;
        }
    }

}
