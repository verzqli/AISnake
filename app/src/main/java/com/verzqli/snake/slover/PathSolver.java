package com.verzqli.snake.slover;

import android.util.Log;

import com.verzqli.snake.Direct;
import com.verzqli.snake.PointType;
import com.verzqli.snake.Snake;
import com.verzqli.snake.SnakePoint;
import com.verzqli.snake.TableCell;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

//    private List<Direct> shortestPathToTail() {
//    }

    public List<Direct> pathTo(SnakePoint point, String type) {
        PointType oldType = map.point(point).getType();
        map.point(point).setType(PointType.EMPTY);
        List<Direct> path = longestPathToPoint(point);
        map.point(point).setType(oldType);
        return path;
    }

    public List<Direct> longestPathToPoint(SnakePoint point) {
        List<Direct> shortPath = shortestPathToPoint(point);
        if (shortPath.size() == 0) {
            return shortPath;
        }
        resetTableCell();
        SnakePoint head = snake.getHead();
        SnakePoint curPoint = head;
        //把最短路径的visit设为true
        tableCells[curPoint.getX()][curPoint.getY()].setVisit(true);
        for (Direct d : shortPath) {
            curPoint = curPoint.nextDirectPoint(d);
            tableCells[curPoint.getX()][curPoint.getY()].setVisit(true);
        }
        curPoint = head;
        int index = 0;
        SnakePoint nextPoint = null;
        SnakePoint curVartualPoint = null;
        SnakePoint nextVartualPoint = null;
        Direct[] diffDirect = new Direct[2];
        while (true) {
            Direct curDirect = shortPath.get(index);
            nextPoint = curPoint.nextDirectPoint(curDirect);
            if (curDirect == Direct.LEFT || curDirect == Direct.RIGHT) {
                diffDirect[0] = Direct.UP;
                diffDirect[1] = Direct.DOWN;
            } else if (curDirect == Direct.UP || curDirect == Direct.DOWN) {
                diffDirect[0] = Direct.LEFT;
                diffDirect[1] = Direct.RIGHT;
            }
            boolean isValidPoint = false;
            for (Direct d : diffDirect) {
                curVartualPoint = curPoint.nextDirectPoint(d);
                nextVartualPoint = nextPoint.nextDirectPoint(d);
                if (isValid(curVartualPoint) && isValid(nextVartualPoint)) {
                    tableCells[curVartualPoint.getX()][curVartualPoint.getY()].setVisit(true);
                    tableCells[nextVartualPoint.getX()][nextVartualPoint.getY()].setVisit(true);
                    shortPath.add(index, d);
                    shortPath.add(index + 2, Direct.opposite(d));
                    isValidPoint = true;
                    break;
                }
            }
            if (!isValidPoint) {
                curPoint = nextPoint;
                index += 1;
                if (index >= shortPath.size()) {
                    break;
                }
            }
        }
        return shortPath;
    }

    public List<Direct> shortestPathToPoint(SnakePoint endPoint) {
        resetTableCell();
        SnakePoint head = snake.getHead();
        tableCells[head.getX()][head.getY()].setDistance(0);
        Deque<SnakePoint> path = new LinkedList<SnakePoint>();
        path.add(head);
        Direct statrDirect;
        while (path.size()>0) {
            SnakePoint curPoint = path.pollFirst();
            if (curPoint.isEqual(endPoint)) {
                return buildPath(head, endPoint);
            }
            if (curPoint == head) {
                statrDirect = snake.getDirect();
            } else {
                statrDirect = tableCells[curPoint.getX()][curPoint.getY()].getParent().directToPoint(curPoint);
            }
            SnakePoint[] allDirect = curPoint.allDirect(statrDirect);
            Log.i("aaa", "当前点: path==  "+path.size());
            Log.i("aaa", "当前点: "+curPoint.toString());
            for (int i = 0, length = allDirect.length; i < length; i++) {
                SnakePoint point = allDirect[i];
                Log.i("aaa", "当前四个点: "+ isValid(point));
                if (isValid(point)) {
                    TableCell cell = tableCells[point.getX()][point.getY()];
                    if (cell.getDistance() == 1000) {
                        Log.i("aaa", "当前存在点: "+ point.toString());
                        cell.setParent(curPoint);
                        cell.setDistance(tableCells[point.getX()][point.getY()].getDistance() + 1);
                        path.addLast(point);
                    }
                }
            }
            Log.i("aaa", "当前存在点:======================== ");

        }
        return new ArrayList<>();
    }

    private List<Direct> buildPath(SnakePoint head, SnakePoint endPoint) {
        List<Direct> path = new ArrayList<>();
        SnakePoint temp = endPoint;
        while (temp != head) {
            SnakePoint parent = tableCells[temp.getX()][temp.getY()].getParent();
            path.add(0, parent.directToPoint(temp));
            temp = parent;
        }
        return path;
    }

    private boolean isValid(SnakePoint snakePoint) {
        return map.isSafe(snakePoint) && !tableCells[snakePoint.getX()][snakePoint.getY()].isVisit();
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
