package org.game.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.nio.file.Paths;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.game.engine.Engine;
import org.game.gamescreen.Game;
import org.game.gamescreen.Game.RotatableLabel;
import org.game.home.WelcomeScreen;
import org.game.util.GenerateRandom;
import org.game.util.constants.AppContext;

public class MainWindow {

    private static boolean isRunning = false;
    
    private static final String TITLE = "Flappy Bird";

    private JFrame jframe;
    private JPanel jpanel;

    private static final String ICON_PATH = Paths.get(AppContext.SYSTEM, "src\\main\\resources\\favicon.png").toString();
    private static final ImageIcon imageIcon = new ImageIcon(ICON_PATH);
    
    public MainWindow() {

        jframe = new JFrame(TITLE);
        setWindow(jframe);
        setIcon(jframe);

        initPanel(jframe);

        displayWindow(jframe);

        new WelcomeScreen(jframe);
        
        if (!isRunning) {
            jframe.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Map<String, RotatableLabel> assets = new Game().initGame(jframe);
                    new Engine().run(jframe, assets);
                    isRunning = true;

                    jframe.removeMouseListener(this);
                }
            });
        }
    }

    private static void setWindow(JFrame jframe) {
        jframe.setSize(AppContext.WIDTH, AppContext.HEIGHT);
        jframe.setPreferredSize(new Dimension(AppContext.WIDTH, AppContext.HEIGHT));
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
    }

    private static void setIcon(JFrame jframe) {
        jframe.setIconImage(imageIcon.getImage());
    }

    private static void displayWindow(JFrame jframe) {
        jframe.setVisible(true);
    }

    private JPanel drawBackground() {
        jpanel = new JPanel() {
            int rand = GenerateRandom.generateRandom(0, 2);
            Image img = new ImageIcon(AppContext.BACKGROUNDS[rand]).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        return jpanel;
    }

    private void initPanel(JFrame jframe) {
        jpanel = drawBackground();
        jframe.setContentPane(jpanel);
    }
}
