package com.verzqli.snake;

import java.util.Random;

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

    public SnakePoint nextDirectPoint(Direct direct) {
        switch (direct) {
            case UP:
                return new SnakePoint(x - 1, y);
            case LEFT:
                return new SnakePoint(x, y - 1);
            case RIGHT:
                return new SnakePoint(x, y + 1);
            case DOWN:
                return new SnakePoint(x + 1, y);
            default:
                return this;
        }
    }

    public Direct directToPoint(SnakePoint curPoint) {
        if (this.x == curPoint.x) {
            int diff = this.y - curPoint.y;
            if (diff == 1) {
                return Direct.LEFT;
            } else if (diff == -1) {
                return Direct.RIGHT;
            }
        }
        if (this.y == curPoint.y) {
            int diff = this.x - curPoint.x;
            if (diff == 1) {
                return Direct.UP;
            } else if (diff == -1) {
                return Direct.DOWN;
            }
        }
        return Direct.NONE;
    }

    public SnakePoint[] allDirect(Direct direct) {
        SnakePoint[] allDirect = new SnakePoint[4];
        allDirect[0] = nextDirectPoint(direct);
        int i = new Random().nextInt(3);
        for (Direct d : Direct.values()) {
            if (d != direct) {
                if (i == 0) {
                    i = 1;
                } else if (i > 3) {
                    i = i - 3;
                }
                allDirect[i++] = nextDirectPoint(d);
            }
        }

        return allDirect;

    }
    public boolean isEqual(SnakePoint point){
        return this.x==point.getX()&&this.y==point.getY();
    }

    @Override
    public String toString() {
        return "SnakePoint[" + x + " , " + y + "]";
    }
}

