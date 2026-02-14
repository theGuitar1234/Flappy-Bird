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
    private static Clip clipHit;

    private Sound() {}

    public static void init() {
        try {
            File flap = new File(AppContext.FLAP_AUD);
            File hit = new File(AppContext.HIT_AUD);

            AudioInputStream flapAudioInputStream = AudioSystem.getAudioInputStream(flap);
            AudioInputStream hitAudioInputStream = AudioSystem.getAudioInputStream(hit);

            clipFlap = AudioSystem.getClip();
            clipHit = AudioSystem.getClip();
            clipFlap.open(flapAudioInputStream);
            clipHit.open(hitAudioInputStream);

        } catch(IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
            clipFlap = null;
            clipHit = null;
        }
    }

    public static void flap() {
        if (clipFlap == null) return;
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

    public static void hit() {
        if (clipHit == null) return;
        clipHit.stop();
        clipHit.setFramePosition(0);
        clipHit.start();
    }

    public static void stopHit() {
        if (clipHit == null) return;
        
        clipHit.stop();
        clipHit.setFramePosition(0);
    }

}
