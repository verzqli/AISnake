package com.verzqli.snake.slover;

import com.verzqli.snake.Direct;
import com.verzqli.snake.Snake;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/11
 *     desc  :
 * </pre>
 */
public class HamiltonSolver extends BaseSolver {
    private PathSolver mPathSolver;
    public HamiltonSolver(Snake snake) {
        super(snake);
        mPathSolver = new PathSolver(snake);
        buildHaniltonCircle();
    }

    private void buildHaniltonCircle() {
    }

    @Override
    public Direct nextDirect() {

        return null;
    }
}
