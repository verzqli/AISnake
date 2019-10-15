package com.verzqli.snake.slover;

import android.util.Log;

import com.verzqli.snake.Direct;
import com.verzqli.snake.Snake;
import com.verzqli.snake.SnakePoint;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/15
 *     desc  :
 * </pre>
 */
public class GreedySolver extends BaseSolver {
    private PathSolver mPathSolver;

    public GreedySolver(Snake snake) {
        super(snake);
        mPathSolver = new PathSolver(snake);
    }

    @Override
    public Direct nextDirect() {
        Snake copySnake = snake.copy();
        mPathSolver.setSnake(copySnake);
        List<Direct> pathToFoodCopy;
        if (snake.getLength()>map.getAreaLength()*0.5f){
            pathToFoodCopy= mPathSolver.longestPathToTail();
        }else{
            pathToFoodCopy= mPathSolver.shortPathToFood();
        }
        if (pathToFoodCopy.size() > 0) {
            copySnake.moveByPath(pathToFoodCopy);
            List<Direct> pathToTailCopy = mPathSolver.longestPathToTail();
            if (pathToTailCopy.size() > 0) {
                mPathSolver.setSnake(snake);
                return pathToFoodCopy.get(0);
            }
        }
        mPathSolver.setSnake(snake);
        List<Direct> pathToTail = mPathSolver.longestPathToTail();
        if (pathToTail.size() > 0) {
            return pathToTail.get(0);
        }
        SnakePoint head = snake.getHead();
        Direct curDirect = snake.getDirect();
        int maxDistance = 0;
        SnakePoint[] allDirect = head.allDirect(curDirect);
        for (SnakePoint point : allDirect) {
            if (map.isSafe(point)) {
                Log.i("aaaaaa", "nextDirect: 安全点"+point.toString());
                int pointDistance = ManhattanDistance(point, map.getFood());
                if (pointDistance >= maxDistance) {
                    maxDistance = pointDistance;
                    curDirect = head.directToPoint(point);
                }
            }
        }
        Log.i("aaaaaa", "nextDirect: 安全方向"+curDirect);
    return curDirect;
    }

    private int ManhattanDistance(SnakePoint point, SnakePoint point1) {
        return Math.abs(point.getX() - point1.getX()) + Math.abs(point.getY() - point1.getY());
    }
}
