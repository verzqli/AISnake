package com.verzqli.snake.slover;

import com.verzqli.snake.Direct;
import com.verzqli.snake.Map;
import com.verzqli.snake.Snake;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/11
 *     desc  :
 * </pre>
 */
public abstract class BaseSolver {
    public Snake snake;
    public Map map;
    BaseSolver(Snake snake){
        this.snake = snake;
        this.map =snake.getMap();
    }
    public abstract Direct nextDirect();

    public Snake getSnake() {
        return snake;
    }

    public Map getMap() {
        return map;
    }
}
