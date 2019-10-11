package com.verzqli.snake;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/11
 *     desc  :
 * </pre>
 */
public class Snake {
    private Map map;
    private Direct direct;
    private Direct nextDirect;
    private int steps;

    public Map getMap() {
        return map;
    }

    public Direct getDirect() {
        return direct;
    }

    public Direct getNextDirect() {
        return nextDirect;
    }

    public int getSteps() {
        return steps;
    }
}
