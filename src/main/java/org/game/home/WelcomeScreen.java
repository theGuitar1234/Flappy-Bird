package org.game.home;

import java.awt.Dimension;
import java.awt.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.game.util.constants.AppContext;

public class WelcomeScreen {

    private JPanel jpanel;

    private JLabel bird;
    private JLabel base;
    private JLabel startGame;

    public WelcomeScreen(JFrame jframe) {

        jpanel = new JPanel();
        bird = new JLabel();
        base = new JLabel();
        startGame = new JLabel();

        initPanel(jpanel);

        drawWelcomeScreen(jpanel, bird, base, startGame);
        welcomeAnimation(bird, startGame);

        JPanel root = (JPanel) jframe.getContentPane();
        root.add(jpanel);
        root.revalidate();
        root.repaint();
    }

    private void welcomeAnimation(JLabel bird, JLabel startGame) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            int n = 0;
            int indecrement = 1;
            int min = AppContext.HEIGHT / 8;
            int max = startGame.getY();
            
            @Override
            public void run() {
                if (n == AppContext.BIRD_PATHS[AppContext.RAND_BIRD].length) n = 0;
                bird.setIcon(new ImageIcon(AppContext.BIRD_PATHS[AppContext.RAND_BIRD][n]));
                n++;

                if (startGame.getY() < max) {
                    indecrement *= -1;
                } else if (startGame.getY() > min) {
                    indecrement *= -1;
                }

                startGame.setLocation(startGame.getX(), startGame.getY() + indecrement);
            }
        };

        timer.schedule(timerTask, 0, AppContext.REFRESH);
    }

    private void drawWelcomeScreen(JPanel jpanel, JLabel bird, JLabel base, JLabel startGame) {
        bird.setSize(bird.getPreferredSize());
        base.setSize(base.getPreferredSize());
        startGame.setSize(startGame.getPreferredSize());

        bird.setIcon(new ImageIcon(AppContext.BIRD_PATHS[AppContext.RAND_BIRD][0]));
        base.setIcon(new ImageIcon(AppContext.BASE));
        startGame.setIcon(new ImageIcon(AppContext.START_GAME));
 
        Dimension b = bird.getPreferredSize();
        Dimension ba = base.getPreferredSize();

        int xBird = (jpanel.getWidth() - b.width) / 16;
        int yBird = (int) ((jpanel.getHeight() - b.height) / 1.5);

        int xBase = 0;
        int yBase = jpanel.getHeight() + AppContext.BASE_HEIGHT;

        bird.setBounds(new Rectangle(xBird, yBird, b.width, b.height));
        base.setBounds(new Rectangle(xBase, yBase, ba.width, ba.height));

        Dimension s = startGame.getPreferredSize();

        int xStartGame = (jpanel.getWidth() - s.width) / 5;
        int yStartGame = (jpanel.getHeight() - s.height) / 2;

        startGame.setBounds(new Rectangle(xStartGame, yStartGame, s.width, s.height));

        jpanel.add(bird);
        jpanel.add(base);
        jpanel.add(startGame);
    }

    private void initPanel(JPanel jpanel) {
        jpanel.setSize(AppContext.HEIGHT, AppContext.WIDTH);
        jpanel.setPreferredSize(new Dimension(AppContext.WIDTH, AppContext.HEIGHT));
        jpanel.setVisible(true);
        jpanel.setOpaque(false);
        jpanel.setLayout(null);
    }
}
