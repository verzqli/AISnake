package com.verzqli.snake;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/11
 *     desc  :
 * </pre>
 */
public class SnakePoint {
    private int x;
    private int y;

    public SnakePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public SnakePoint nextSnakePoint(Direct direct){
        switch (direct) {
            case UP:
                return new SnakePoint(x,y-1);
            case LEFT:
                return new SnakePoint(x-1,y);
            case RIGHT:
                return new SnakePoint(x+1,y);
            case DOWN:
                return new SnakePoint(x,y+1);
            default:
                return this;
        }
    }
}
