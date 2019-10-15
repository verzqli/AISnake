package com.verzqli.snake.slover;

import android.util.Log;

import com.verzqli.snake.Direct;
import com.verzqli.snake.HamiltonTableCell;
import com.verzqli.snake.Snake;
import com.verzqli.snake.SnakePoint;
import com.verzqli.snake.TableCell;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/11
 *     desc  :
 * </pre>
 */
public class HamiltonSolver extends BaseSolver {
    private PathSolver mPathSolver;
    private HamiltonTableCell[][] mTableCells;

    public HamiltonSolver(Snake snake) {
        super(snake);
        mPathSolver = new PathSolver(snake);
        int row = snake.getMap().getRow();
        int col = snake.getMap().getCol();

        mTableCells = new HamiltonTableCell[col][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mTableCells[i][j] = new HamiltonTableCell();
            }
        }
        buildHamiltonCircle();
    }

    private void buildHamiltonCircle() {
        List<Direct> path = mPathSolver.longestPathToTail();
        SnakePoint curPoint = snake.getHead();
        int index = 0;
        for (Direct direct : path) {
            mTableCells[curPoint.getX()][curPoint.getY()].setDirect(direct);
            mTableCells[curPoint.getX()][curPoint.getY()].setIndex(index);
            curPoint = curPoint.nextDirectPoint(direct);
            index++;
        }
        //这里长度要减一是因为蛇头不能算进去 不然会循环跑
        for (int i = 0, length = snake.getLength(); i < length - 1; i++) {
            mTableCells[curPoint.getX()][curPoint.getY()].setDirect(snake.getDirect());
            mTableCells[curPoint.getX()][curPoint.getY()].setIndex(index);
            curPoint = curPoint.nextDirectPoint(snake.getDirect());
            index++;
        }

    }

    @Override
    public Direct nextDirect() {
        SnakePoint head = snake.getHead();
        Direct nextDirect = mTableCells[head.getX()][head.getY()].getDirect();

        return nextDirect;
    }
}
