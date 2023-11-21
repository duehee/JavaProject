package Roulette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomRoulette extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private int angle = 0;
    private boolean spinning = false;
    private Timer timer;

    public RandomRoulette() {
        setTitle("Random Timed Roulette Animation");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton showRouletteButton = new JButton("룰렛 보기");
        showRouletteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRouletteDialog();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showRouletteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showRouletteDialog() {
        JFrame dialogFrame = new JFrame("룰렛");
        dialogFrame.setSize(400, 400);
        dialogFrame.setLocationRelativeTo(null);

        JButton spinButton = new JButton("SPIN");
        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!spinning) {
                    spinning = true;
                    spinRoulette(dialogFrame);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(spinButton);

        dialogFrame.add(buttonPanel, BorderLayout.SOUTH);

        RoulettePanel roulettePanel = new RoulettePanel();
        dialogFrame.add(roulettePanel, BorderLayout.CENTER);

        dialogFrame.setVisible(true);
    }

    private void spinRoulette(JFrame dialogFrame) {
        Random random = new Random();
        int targetAngle = random.nextInt(360) + 3600; // 더 빨리 돌기 위해 각도 증가

        int spinDuration = random.nextInt(2000) + 2000; // 2초에서 4초 사이의 랜덤한 시간

        timer = new Timer(10, new ActionListener() {
            int elapsedTime = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                angle = (angle + 5) % 360;
                dialogFrame.repaint();
                elapsedTime += 10;

                if (elapsedTime >= spinDuration) {
                    ((Timer) e.getSource()).stop();
                    spinning = false;
                }
            }
        });

        timer.start();
    }

    private class RoulettePanel extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(getWidth(), getHeight()) / 4;

            int x = centerX - radius;
            int y = centerY - radius;

            g.setColor(Color.RED);
            g.fillArc(x, y, 2 * radius, 2 * radius, angle, 45);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RandomRoulette().setVisible(true);
            }
        });
    }
}