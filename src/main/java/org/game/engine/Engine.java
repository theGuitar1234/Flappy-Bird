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
import org.game.util.constants.AppContext;

public class Engine {

    private static final double V = 15;
    private static final int g = 25;

    private static double v;
    private static double t;
    private static double decrement;
    private static boolean isBound = false;
    
    public void run(JFrame jframe, Map<String, JLabel> assets) {

        Sound.init();

        JLabel bird = assets.get(AppContext.BIRD_KEY);

        jframe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initAnimation(jframe, bird);
                initFlap(jframe);
            }
        });
    }

    private void initFlap(JFrame jframe) {
        Sound.flap();
    }

    private void initAnimation(JFrame jframe, JLabel bird) {

        decrement = AppContext.REFRESH / 1000.0;
        System.out.println(decrement);
        v = V;

        if (isBound) return;
        isBound = true;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override 
            public void run() {

                if (bird.getY() > jframe.getHeight() - bird.getHeight() + 350) {
                    timer.cancel();
                    System.out.println("Timer cancelled");
                }

                double s = v*t - (g*Math.pow(t, 2))/2;
                t += decrement;

                int x = bird.getX();
                int y = (int) (bird.getY() - s);

                bird.setLocation(new Point(x, y));
            }
        };

        System.out.println(timer);
        timer.schedule(timerTask, 0, AppContext.REFRESH);
    }
}