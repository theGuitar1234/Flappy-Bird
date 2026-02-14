package org.game.util.constants;

import java.nio.file.Paths;

import org.game.util.GenerateRandom;

public final class AppContext {

    public static final int BASE_HEIGHT = 112;

    public static final int RAND_BIRD = GenerateRandom.generateRandom(0, 3);

    public static final String BIRD_KEY = "bird";

    public static final int REFRESH = 64;
        
    public static final int WIDTH = 336;
    public static final int HEIGHT = 597;
    
    public static final String SYSTEM = Paths.get(System.getProperty("user.dir")).toString();

    public static final String BASE = Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\base.png").toString();

    public static final String[] BACKGROUNDS = {
        Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\background-day.png").toString(),
        Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\background-night.png").toString(),
    };

    public static final String[][] BIRD_PATHS = {
        {
            Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\bluebird-downflap.png").toString(),
            Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\bluebird-midflap.png").toString(),
            Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\bluebird-upflap.png").toString()
        },
        {
            Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\redbird-downflap.png").toString(),
            Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\redbird-midflap.png").toString(),
            Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\redbird-upflap.png").toString()
        },
        {
            Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\yellowbird-downflap.png").toString(),
            Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\yellowbird-midflap.png").toString(),
            Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\yellowbird-upflap.png").toString()
        },
    };

    public static final String START_GAME = Paths.get(AppContext.SYSTEM, "src\\main\\resources\\sprites\\message.png").toString();

    public static final String FLAP_AUD = Paths.get(AppContext.SYSTEM, "src\\main\\resources\\audio\\wing.wav").toString();
    public static final String HIT_AUD = Paths.get(AppContext.SYSTEM, "src\\main\\resources\\audio\\hit.wav").toString();
}
