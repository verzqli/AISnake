package com.verzqli.snake;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/11
 *     desc  :
 * </pre>
 */
public enum Direct {
    NONE,
    LEFT,
    UP,
    RIGHT,
    DOWN;

    public Direct opposite(Direct direct) {
        switch (direct) {
            case UP:
                return DOWN;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case DOWN:
                return UP;
            default:
                return NONE;
        }
    }
}
