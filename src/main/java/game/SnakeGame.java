package game;

import models.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JPanel implements ActionListener {
    private static final int SCALE = 32;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private int speed = 10;
    private Graphics globalGraphics;
    private Snake s = new Snake(10, 10, 9, 10);
    private Timer timer = new Timer(1000 / speed, this);
    private JFrame jFrame = new JFrame("Snake");

    public SnakeGame() {
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public static int getSCALE() {
        return SCALE;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }


    public void paint(Graphics graphics) {
        globalGraphics = graphics.create();
    }

    private void drawPoisonApple(Graphics graphics) {
        paintPoisonApple(graphics);
    }

    private void drawGame(Graphics graphics) {
        paintGrid(graphics);
        paintApple(graphics);
        paintSnake(graphics);
        paintScore(graphics);
    }

    private void paintGrid(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        for (int x = 0; x <= WIDTH * SCALE; x += SCALE) {
            graphics.setColor(Color.black);
            graphics.drawLine(x, 0, x, HEIGHT * SCALE);
        }
        for (int y = 0; y <= HEIGHT * SCALE; y += SCALE) {
            graphics.setColor(Color.black);
            graphics.drawLine(0, y, WIDTH * SCALE, y);
        }
    }

    private void paintPoisonApple(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval(s.poisonApple.getPositionX() * SCALE + 2, s.poisonApple.getPositionY() * SCALE + 6,
                SCALE - 10, SCALE - 10);
        graphics.drawString("Red apple is a poison apple. Be careful! ", 0, HEIGHT * SCALE - 80);
    }

    private void paintApple(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.fillOval(s.apple.getPositionX() * SCALE + 4, s.apple.getPositionY() * SCALE + 4,
                SCALE - 10, SCALE - 10);
    }

    private void paintSnake(Graphics graphics) {
        for (int d = 1; d < s.getLength(); d++) {
            graphics.setColor(Color.CYAN);
            graphics.fillRect(s.getSnakeX()[d] * SCALE + 1, s.getSnakeY()[d] * SCALE + 1,
                    SCALE - 6, SCALE - 6);
            graphics.setColor(Color.PINK);
            graphics.fillRect(s.getSnakeX()[0] * SCALE + 1, s.getSnakeY()[0] * SCALE + 1,
                    SCALE - 6, SCALE - 6);
        }
        graphics.setColor(Color.white);
    }

    private void paintScore(Graphics graphics) {
        graphics.drawString("Score: " + s.getScore(), 0, HEIGHT * SCALE - 10);
        graphics.drawString("Best score: " + s.getBestScore(), 0, HEIGHT * SCALE - 30);
    }

    public void CreateWindow() {
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setSize(WIDTH * SCALE + 10, HEIGHT * SCALE + 20);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new SnakeGame());
        jFrame.setVisible(true);
        jFrame.setVisible(true);
    }

    private void addPoisonApple() {
        if (s.getLength() > 7) {
            drawPoisonApple(globalGraphics);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        drawGame(globalGraphics);
        s.move();
        s.transitionFunc();
        addPoisonApple();
        s.eatApple();
        timer.stop();
        s.eatPoisonApple();
        timer.start();
        s.getBestsScore();
        timer.stop();
        s.eatMyself();
        timer.start();
        repaint();
    }

    public class KeyBoard extends KeyAdapter {
        public void keyPressed(KeyEvent event) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (s.getDirection() != s.ONE) {
                        s.setDirection(3);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (s.getDirection() != s.THREE) {
                        s.setDirection(1);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (s.getDirection() != s.TWO) {
                        s.setDirection(0);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (s.getDirection() != s.NULL) {
                        s.setDirection(2);
                    }
                    break;
            }
        }
    }
}

