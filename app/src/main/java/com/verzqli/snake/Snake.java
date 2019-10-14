package com.verzqli.snake;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/11
 *     desc  :
 * </pre>
 */
public class Snake {
    private Map map;
    private Direct curDirect;
    private Direct nextDirect;
    private Deque<SnakePoint> mSnakeBody;
    private int steps;

    public Snake(Map map, Direct direct, Deque<SnakePoint> snakeBody) {
        this.map = map;
        this.curDirect = direct;
        this.nextDirect = direct;
        this.mSnakeBody = snakeBody;
        reset();
    }

    private void reset() {
        curDirect = Direct.RIGHT;
        for (SnakePoint point : mSnakeBody) {
            map.point(point).setType(PointType.BODY);
        }
        map.point(getHead()).setType(PointType.HEAD);
    }

    public Map getMap() {
        return map;
    }

    public void move() {
        move(null);
    }

    public void move(Direct direct) {
        if (direct != null) {
            nextDirect = direct;
        }
        SnakePoint newHead = getHead().nextDirectPoint(nextDirect);
        mSnakeBody.addFirst(newHead);
        if (map.point(newHead).getType() == PointType.FOOD) {

        } else {
            removeTail();
        }
        map.point(getHead()).setType(PointType.HEAD);
        curDirect = nextDirect;
    }

    public SnakePoint getHead() {
        return mSnakeBody.getFirst();
    }

    public SnakePoint getTail() {
        return mSnakeBody.getLast();
    }

    public void removeTail() {
        map.point(getTail()).setType(PointType.EMPTY);
        mSnakeBody.removeLast();
    }

    public Direct getDirect() {
        return curDirect;
    }


    public Direct getNextDirect() {
        return nextDirect;
    }

    public void setNextDirect(Direct nextDirect) {
        this.nextDirect = nextDirect;
    }

    public int getSteps() {
        return steps;
    }

    public int getLength() {
        return mSnakeBody.size();
    }
}
