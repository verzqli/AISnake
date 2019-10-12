package com.verzqli.snake.slover;

import com.verzqli.snake.Direct;
import com.verzqli.snake.PointType;
import com.verzqli.snake.Snake;
import com.verzqli.snake.SnakePoint;
import com.verzqli.snake.TableCell;

import java.util.List;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/12
 *     desc  :
 * </pre>
 */
public class PathSolver extends BaseSolver {
    private TableCell[][] tableCells;
    private int row, col;

    PathSolver(Snake snake) {
        super(snake);
        row = snake.getMap().getRow();
        col = snake.getMap().getCol();

        tableCells = new TableCell[col][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tableCells[i][j] = new TableCell();
            }
        }
    }


    private List<Direct> longestPathToTail() {
        return pathTo(snake.getTail(), "longest");
    }

    private List<Direct> shortestPathToTail() {
    }

    private List<Direct> pathTo(SnakePoint point, String type) {
        PointType oldType = map.point(point).getType();
        map.point(point).setType(PointType.EMPTY);
        List<Direct> path = longestPathToPoint(point);
    }

    private List<Direct> longestPathToPoint(SnakePoint point) {
        List<Direct> shortPath = shortestPathToPoint(point);

    }

    private List<Direct> shortestPathToPoint(SnakePoint point) {
        resetTableCell();

    }

    @Override
    public Direct nextDirect() {
        return null;
    }

    private void resetTableCell() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tableCells[i][j].reset();
            }
        }
    }


}
