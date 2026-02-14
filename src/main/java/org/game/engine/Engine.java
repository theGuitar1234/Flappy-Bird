package org.game.engine;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
// import java.util.Timer;
// import java.util.TimerTask;

import javax.swing.Timer;
import javax.swing.JFrame;

import org.game.engine.sound.Sound;
import org.game.gamescreen.Game.RotatableLabel;
import org.game.util.constants.AppContext;

public class Engine {

    private static final double V = 9;
    private static final int g = 9;

    private static double v;
    private static double t;
    private static double animationinDecrement;
    private static double rotationInDecrement;
    private static double rotation;

    private static boolean isInitAnimationBound = false;
    private static boolean isInitRotateBound = false;
    private static volatile boolean isRunning = false;
    private static boolean isBound = false;

    private static Timer animationTimer;
    private static Timer rotateTimer;
    private static MouseAdapter mouseAdapter;
    
    public void run(JFrame jframe, Map<String, RotatableLabel> assets) {

        isRunning = true;
        
        Sound.init();

        RotatableLabel bird = assets.get(AppContext.BIRD_KEY);

        if (!isBound) {
            initMouseListener(jframe, bird);
            isBound = true;
        }  
    }

    private boolean detectCollision(JFrame jframe, RotatableLabel bird) {
        return (bird.getY() > jframe.getHeight() - bird.getHeight() - AppContext.BASE_HEIGHT);
    }
 
    private void initMouseListener(JFrame jframe, RotatableLabel bird) {
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (!isRunning) {
                    jframe.removeMouseListener(this);
                    return;
                }
                
                initAnimation(jframe, bird);
                initRotate(jframe, bird);
                initFlap(jframe);
            }
        };
        jframe.addMouseListener(mouseAdapter);
    }

    private void initFlap(JFrame jframe) {
        Sound.flap();
    }

    private void initAnimation(JFrame jframe, RotatableLabel bird) {

        animationinDecrement = 0.12;
        v = V;
        t = 0;

        if (isInitAnimationBound) return;
        isInitAnimationBound = true;

        animationTimer = new Timer(AppContext.REFRESH, e -> {
            if (!isRunning) {
                animationTimer.stop();
                return;
            }

            double s = v - g*(Math.pow(t, 2));
            t += animationinDecrement;

            int x = bird.getX();
            int y = (int) (bird.getY() - s);

            bird.setLocation(new Point(x, y));

            if (detectCollision(jframe, bird)) {
                gameOver(jframe);
            }
        });

        animationTimer.start();
        //animationTimer.schedule(timerTask, 0, AppContext.REFRESH);
    }

    private void initRotate(JFrame jframe, RotatableLabel bird) {

        rotation = 0;
        rotationInDecrement = 1.5;

        if (isInitRotateBound) return;
        isInitRotateBound = true;

        rotateTimer = new Timer(AppContext.REFRESH, e -> {
            if (!isRunning) {
                rotateTimer.stop();
                return;
            }
            if (rotation <= -90.0) rotationInDecrement *= -1;
               
            rotation -= rotationInDecrement;
            bird.setRotationDegrees(rotation);
        });

        rotateTimer.start();
        //rotateTimer.schedule(timerTask, 0, AppContext.REFRESH);
    }

    private void gameOver(JFrame jframe) {
        isRunning = false;

        Sound.hit();
        //Sound.stopHit();
        Sound.close();

        if (animationTimer != null) {
            animationTimer.stop();
            animationTimer = null;
        }
        if (rotateTimer != null) {
            rotateTimer.stop();
            rotateTimer = null;
        }

        if (mouseAdapter != null) {
            jframe.removeMouseListener(mouseAdapter);
            mouseAdapter = null;
        }
            
        jframe.removeMouseListener(mouseAdapter);

        isInitAnimationBound = false;
        isInitRotateBound = false;
    }
}