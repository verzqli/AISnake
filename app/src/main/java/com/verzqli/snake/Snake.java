package com.verzqli.snake;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
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
    private boolean isDead;
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

    public boolean move(Direct direct) {
        if (direct != null) {
            nextDirect = direct;
        }
        if (isDead || direct == null || map.isFull() || nextDirect == Direct.opposite(direct)) {
            return true;
        }
        Log.i("aaaa", "move: 新方向"+direct);
        if (map.getFood()!=null){
            Log.i("aaaa", "move: 新FOOD"+map.getFood().toString());
        }
        SnakePoint newHead = getHead().nextDirectPoint(nextDirect);
        Log.i("aaaa", "move: 新点"+newHead+"===========================");
        map.point(getHead()).setType(PointType.BODY);
        mSnakeBody.addFirst(newHead);
        if (map.point(newHead).getType() == PointType.FOOD) {
            map.removeFood();
        } else {
            removeTail();
        }
        map.point(getHead()).setType(PointType.HEAD);
        curDirect = nextDirect;
        return false;
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

    public Snake copy() {
        Map copyMap = getMap().copy();
        Snake copySnake = new Snake(copyMap, curDirect, new LinkedList<SnakePoint>(mSnakeBody));
        copySnake.nextDirect = this.nextDirect;
        copySnake.isDead = this.isDead;
        copySnake.steps = this.steps;
        return copySnake;
    }

    public void moveByPath(List<Direct> pathToFood) {
        for (Direct d : pathToFood) {
            move(d);
        }
    }
}
