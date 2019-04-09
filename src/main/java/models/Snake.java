package models;

import game.SnakeGame;

import javax.swing.*;


public class Snake implements SnakeService {
    public final int NULL = 0;
    public final int ONE = 1;
    public final int TWO = 2;
    public final int THREE = 3;
    public Apple apple = new Apple(Math.abs((int) (Math.random() * SnakeGame.getWIDTH() - 1)),
            Math.abs((int) (Math.random() * SnakeGame.getHEIGHT() - 1)));
    public PoisonApple poisonApple = new PoisonApple(Math.abs((int) (Math.random() * SnakeGame.getWIDTH() - 4)),
            Math.abs((int) (Math.random() * SnakeGame.getHEIGHT() - 2)));
    private int length = 2;
    private int direction;
    private int score;
    private int bestScore;
    private int snakeX[] = new int[SnakeGame.getWIDTH() * SnakeGame.getSCALE()];
    private int snakeY[] = new int[SnakeGame.getHEIGHT() * SnakeGame.getSCALE()];

    public Snake() {

    }

    public Snake(final int x0, final int y0, final int x1, final int y1) {
        snakeX[0] = x0;
        snakeY[0] = y0;
        snakeX[1] = x1;
        snakeY[1] = y1;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(final int bestScore) {
        this.bestScore = bestScore;
    }

    public int getLength() {
        return length;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(final int direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }


    public int[] getSnakeX() {
        return snakeX;
    }

    public void setSnakeX(final int[] snakeX) {
        this.snakeX = snakeX;
    }

    public int[] getSnakeY() {
        return snakeY;
    }

    public void setSnakeY(final int[] snakeY) {
        this.snakeY = snakeY;
    }

    public void move() {
        for (int l = length; l > 0; l--) {
            snakeX[l] = snakeX[l - 1];
            snakeY[l] = snakeY[l - 1];
        }
        switch (direction) {
            case NULL: {
                snakeX[0]++;
            }
            break;
        }
        switch (direction) {
            case ONE: {
                snakeY[0]++;
            }
            break;
        }
        switch (direction) {
            case TWO: {
                snakeX[0]--;
            }
            break;
        }
        switch (direction) {
            case THREE: {
                snakeY[0]--;
            }
            break;
        }
    }

    @Override
    public void transitionFunc() {
        if (snakeX[0] > SnakeGame.getWIDTH() - 1) {
            snakeX[0] = 0;
        }
        if (snakeX[0] < 0) {
            snakeX[0] = SnakeGame.getWIDTH() - 1;
        }

        if (snakeY[0] > SnakeGame.getHEIGHT() - 1) {
            snakeY[0] = 0;
        }
        if (snakeY[0] < 0) snakeY[0] = SnakeGame.getHEIGHT() - 1;
    }


    @Override
    public void getBestsScore() {
        if (score > bestScore) {
            bestScore = score;
        }
    }

    @Override
    public void eatApple() {
        if ((snakeX[0] == apple.getPositionX()) && (snakeY[0] == apple.getPositionY())) {
            poisonApple.setRandomPosition();
            apple.setRandomPosition();
            score += 10;
            length++;
        }
    }

    @Override
    public void eatPoisonApple() {
        if ((snakeX[0] == poisonApple.getPositionX()) && (snakeY[0] == poisonApple.getPositionY())
                && (length > 7)) {
            JOptionPane.showMessageDialog(null, " " + score);
            poisonApple.setRandomPosition();
            length = 2;
            score = 0;
            direction = 0;
            apple.setRandomPosition();
        }
    }

    @Override
    public void eatMyself() {
        for (int l = 1; l < length; l++) {
            if ((snakeX[l] == apple.getPositionX()) && (snakeY[l] == apple.getPositionY())) {
                apple.setRandomPosition();
                poisonApple.setRandomPosition();
            }
            if ((snakeX[0] == snakeX[l]) && (snakeY[0] == snakeY[l])) {
                JOptionPane.showMessageDialog(null, "" + score);
                length = 2;
                score = 0;
                direction = 0;
                apple.setRandomPosition();
            }
        }
    }

}

