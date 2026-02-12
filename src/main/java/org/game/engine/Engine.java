package org.game.engine;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.game.engine.sound.Sound;
import org.game.gamescreen.Game.RotatableLabel;
import org.game.util.constants.AppContext;

public class Engine {

    private static final double V = 9;
    private static final int g = 9;

    private static double v;
    private static double t;
    private static double animationinDecrement;
    private static double rotationinDecrement;
    private static double rotation;
    private static boolean isInitAnimationBound = false;
    private static boolean isInitRotateBound = false;
    private static boolean isRunning = false;
    
    public void run(JFrame jframe, Map<String, RotatableLabel> assets) {

        isRunning = true;
        
        Sound.init();

        RotatableLabel bird = assets.get(AppContext.BIRD_KEY);

        jframe.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (bird.getY() > jframe.getHeight() - bird.getHeight()) isRunning = false;

                initAnimation(jframe, bird);
                initRotate(jframe, bird);
                initFlap(jframe);
            }
        });
    }

    private void initFlap(JFrame jframe) {
        if (isRunning) Sound.flap();
    }

    private void initAnimation(JFrame jframe, RotatableLabel bird) {

        animationinDecrement = 0.12;
        v = V;
        t = 0;

        if (isInitAnimationBound) return;
        isInitAnimationBound = true;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override 
            public void run() {

                if (!isRunning) timer.cancel();

                //double s = v*t - (g*Math.pow(t, 2))/2;
                double s = v - g*(Math.pow(t, 2));
                t += animationinDecrement;

                int x = bird.getX();
                int y = (int) (bird.getY() - s);

                bird.setLocation(new Point(x, y));
            }
        };

        timer.schedule(timerTask, 0, AppContext.REFRESH);
    }

    private void initRotate(JFrame jframe, RotatableLabel bird) {

        rotation = 0;
        rotationinDecrement = 2;

        if (isInitRotateBound) return;
        isInitRotateBound = true;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!isRunning) timer.cancel();
                if (rotation <= -90.0) rotationinDecrement *= -1;
               
                rotation -= rotationinDecrement;
                bird.setRotationDegrees(rotation);
            }
        };

        timer.schedule(timerTask, 0, AppContext.REFRESH);
    }
}