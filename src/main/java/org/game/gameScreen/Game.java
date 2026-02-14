package org.game.gamescreen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
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
    private RotatableLabel bird;
    private JLabel base;

    private static final int RAND_BIRD = GenerateRandom.generateRandom(0, 3);

    public Map<String, RotatableLabel> initGame(JFrame jframe) {

        jpanel = new JPanel();
        bird = new RotatableLabel();
        base = new JLabel();

        initPanel(jpanel);

        drawGame(jpanel, bird, base);
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

    private void drawGame(JPanel jpanel, RotatableLabel bird, JLabel base) {
        bird.setSize(bird.getPreferredSize());
        bird.setIcon(new ImageIcon(AppContext.BIRD_PATHS[RAND_BIRD][0]));

        Dimension b = bird.getPreferredSize();

        int xBird = (jpanel.getWidth() - b.width) / 16;
        int yBird = (int) ((jpanel.getHeight() - b.height) / 1.5);

        bird.setBounds(new Rectangle(xBird, yBird, b.width, b.height));

        base.setSize(base.getPreferredSize());
        base.setIcon(new ImageIcon(AppContext.BASE));

        Dimension ba = base.getPreferredSize();

        int xBase = 0;
        int yBase = jpanel.getHeight() + AppContext.BASE_HEIGHT;

        base.setBounds(new Rectangle(xBase, yBase, ba.width, ba.height));

        jpanel.add(bird);
        jpanel.add(base);
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

    public static class RotatableLabel extends JLabel {

        private double angleRad = 0.0;

        public double getRotationDegrees() {
            return Math.toDegrees(angleRad);
        }

        public void setRotationDegrees(double degrees) {
            this.angleRad = Math.toRadians(degrees);
            revalidate();
            repaint();
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();

            double sin = Math.abs(Math.sin(angleRad));
            double cos = Math.abs(Math.cos(angleRad));

            int w = (int) Math.ceil(d.width * cos + d.height * sin);
            int h = (int) Math.ceil(d.width * sin + d.height * cos);

            return new Dimension(w, h);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;
            int cy = getHeight() / 2;

            AffineTransform old = g2.getTransform();
            g2.rotate(angleRad, cx, cy);

            super.paintComponent(g2);

            g2.setTransform(old);
            g2.dispose();
        }
        
    }
}
