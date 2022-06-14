package de.shao.visualisierung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Inhalt extends JPanel {

    private final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    private final int SLIDERHIGHT = Pictures.SLIDER.image().getHeight(null);
    private final int SLIDERWIDTH = Pictures.SLIDER.image().getWidth(null);

    private final int TIMER_CONSTANT_IN_MILLIS = 100000;
    private final int STEPS = 2;

    private int win = SLIDERWIDTH * 4;

    private int firstWheelOne = 0;
    private int firstWheelTwo = -SLIDERHIGHT;

    private int secondWheelOne = firstWheelOne + 100;
    private int secondWheelTwo = firstWheelTwo + 100;

    private int thirdWheelOne = firstWheelOne + 50;
    private int thirdWheelTwo = firstWheelTwo + 50;

    private boolean firstWheelRunning = true;
    private boolean secondWheelRunning = true;
    private boolean thirdWheelRunning = true;

    private boolean firstWheelHasStopped = false;
    private boolean secondWheelHasStopped = false;
    private boolean thirdWheelHasStopped = false;


    public Inhalt() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.GRAY);
        init();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                stop();
            }
        });
    }

    private void stop(){
        new Thread(() -> {
            Random random = new Random();
            if (firstWheelRunning){
                win = SLIDERWIDTH * random.nextInt(0,6);
                firstWheelRunning = false;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                secondWheelRunning = false;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                thirdWheelRunning = false;
            }else {
                firstWheelRunning = true;
                secondWheelRunning = true;
                thirdWheelRunning = true;
                init();
            }
        }).start();
    }

    private void init() {
        firstWheel();
        secondWheel();
        thirdWheel();
    }

    private void firstWheel() {
        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(0, TIMER_CONSTANT_IN_MILLIS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                firstWheelOne += STEPS;
                firstWheelTwo += STEPS;

                if (firstWheelOne >= SLIDERHIGHT) firstWheelOne = 0;
                if (firstWheelTwo >= 0) firstWheelTwo = -SLIDERHIGHT;

                if (firstWheelRunning == false && firstWheelOne == win){
                    break;
                }
            }
        }).start();
    }

    private void secondWheel() {
        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(0, TIMER_CONSTANT_IN_MILLIS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                secondWheelOne += STEPS;
                secondWheelTwo += STEPS;

                if (secondWheelOne >= SLIDERHIGHT) secondWheelOne = 0;
                if (secondWheelTwo >= 0) secondWheelTwo = -SLIDERHIGHT;

                if (secondWheelRunning == false && secondWheelOne == win){
                    break;
                }
            }
        }).start();
    }

    private void thirdWheel() {
        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(0, TIMER_CONSTANT_IN_MILLIS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                thirdWheelOne += STEPS;
                thirdWheelTwo += STEPS;

                if (thirdWheelOne >= SLIDERHIGHT) thirdWheelOne = 0;
                if (thirdWheelTwo >= 0) thirdWheelTwo = -SLIDERHIGHT;

                if (thirdWheelRunning == false && thirdWheelOne == win){
                    break;
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(Pictures.SLIDER.image(), (SCREEN_WIDTH/2)-(SLIDERWIDTH/2) - 120, firstWheelOne, null);
        g2d.drawImage(Pictures.SLIDER.image(), (SCREEN_WIDTH/2)-(SLIDERWIDTH/2), secondWheelOne, null);
        g2d.drawImage(Pictures.SLIDER.image(), (SCREEN_WIDTH/2)-(SLIDERWIDTH/2) + 120, thirdWheelOne, null);

        g2d.drawImage(Pictures.SLIDER.image(), (SCREEN_WIDTH/2)-(SLIDERWIDTH/2) - 120, firstWheelTwo, null);
        g2d.drawImage(Pictures.SLIDER.image(), (SCREEN_WIDTH/2)-(SLIDERWIDTH/2), secondWheelTwo, null);
        g2d.drawImage(Pictures.SLIDER.image(), (SCREEN_WIDTH/2)-(SLIDERWIDTH/2) + 120, thirdWheelTwo, null);

        g2d.drawImage(Pictures.BACKGROUND.image(), 0,0,null);
    }
}
