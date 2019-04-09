package models;

import game.SnakeGame;

public class Apple {
    private int positionX;
    private int positionY;

    public Apple() {

    }

    public Apple(final int x, final int y) {
        positionX = x;
        positionY = y;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(final int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(final int positionY) {
        this.positionY = positionY;
    }

    public void setRandomPosition() {
        positionX = Math.abs((int) (Math.random() * SnakeGame.getWIDTH() - 1));
        positionY = Math.abs((int) (Math.random() * SnakeGame.getHEIGHT() - 1));
    }
}






