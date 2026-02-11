package org.game.gameScreen;

import java.awt.Dimension;
import java.awt.Rectangle;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.game.util.GenerateRandom;
import org.game.util.constants.AppContext;

public class Game {

    private JPanel jpanel;
    private JLabel bird;

    private static final int RAND_BIRD = GenerateRandom.generateRandom(0, 3);

    public Map<String, JLabel> initGame(JFrame jframe) {

        jpanel = new JPanel();
        bird = new JLabel();

        initPanel(jpanel);

        drawGame(jpanel, bird);
        flapAnimation(bird);

        JPanel root = (JPanel) jframe.getContentPane();
        root.removeAll();
        root.add(jpanel);
        root.revalidate();
        root.repaint();

        return Map.of(AppContext.BIRD_KEY, bird);
    }

    private void initPanel(JPanel jpanel) {
        jpanel.setSize(AppContext.HEIGHT, AppContext.WIDTH);
        jpanel.setPreferredSize(new Dimension(AppContext.WIDTH, AppContext.HEIGHT));
        jpanel.setVisible(true);
        jpanel.setOpaque(false);
        jpanel.setLayout(null);
    }

    private void drawGame(JPanel jpanel, JLabel bird) {
        bird.setSize(bird.getPreferredSize());
     
        bird.setIcon(new ImageIcon(AppContext.BIRD_PATHS[RAND_BIRD][0]));

        Dimension b = bird.getPreferredSize();

        int xBird = (jpanel.getWidth() - b.width) / 16;
        int yBird = (int) ((jpanel.getHeight() - b.height) / 1.5);

        bird.setBounds(new Rectangle(xBird, yBird, b.width, b.height));

        jpanel.add(bird);
    }

    private void flapAnimation(JLabel bird) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int n = 0;
            @Override
            public void run() {
                if (n == AppContext.BIRD_PATHS[RAND_BIRD].length) n = 0;
                bird.setIcon(new ImageIcon(AppContext.BIRD_PATHS[RAND_BIRD][n]));
                n++;
            }
        };

        timer.schedule(timerTask, 0, AppContext.REFRESH);
    }
}
