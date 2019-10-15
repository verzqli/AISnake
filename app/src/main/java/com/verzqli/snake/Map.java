package com.verzqli.snake;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/11
 *     desc  :
 * </pre>
 */
public class Map {
    private MapPoint mPointTypes[][];
    private int mRow;
    private int mCol;
    private SnakePoint mFood;
    private int areaLength;

    public Map(int row, int col) {
        this.mRow = row;
        this.mCol = col;
        mPointTypes = new MapPoint[mRow][mCol];
        areaLength = (mCol - 2) * (mRow - 2);
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mCol; j++) {
                mPointTypes[i][j] = new MapPoint();
            }
        }
        for (int i = 0; i < mRow; i++) {
            mPointTypes[i][0].setType(PointType.WALL);
            mPointTypes[i][col - 1].setType(PointType.WALL);
        }
        for (int i = 0; i < mCol; i++) {
            mPointTypes[0][i].setType(PointType.WALL);
            mPointTypes[row - 1][i].setType(PointType.WALL);
        }
    }

    public Map copy() {
        Map copy = new Map(mRow, mCol);
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mCol; j++) {
                copy.mPointTypes[i][j] = new MapPoint(mPointTypes[i][j].getType());
            }
        }
        return copy;

    }

    public SnakePoint createFood() {
        List<SnakePoint> empty = new ArrayList<>();
        for (int i = 0; i < mRow - 1; i++) {
            for (int j = 0; j < mCol - 1; j++) {
                if (mPointTypes[i][j].getType() == PointType.EMPTY) {
                    empty.add(new SnakePoint(i, j));
                } else if (mPointTypes[i][j].getType() == PointType.FOOD) {
                    return null;
                }
            }
        }
        int length = empty.size();
        if (length > 0) {
            mFood = empty.get(new Random().nextInt(length));
            point(mFood).setType(PointType.FOOD);
            return mFood;
        } else {
            return null;
        }
    }

    public Boolean haveFood() {
        return mFood != null;
    }

    public SnakePoint getFood() {
        return mFood;
    }

    public void removeFood() {
        if (haveFood()) {
            point(mFood).setType(PointType.EMPTY);
            mFood = null;
        }
    }

    public MapPoint[][] getContent() {
        return mPointTypes;
    }

    public MapPoint point(SnakePoint snakePoint) {
        return mPointTypes[snakePoint.getX()][snakePoint.getY()];
    }

    public int getRow() {
        return mRow;
    }

    public int getCol() {
        return mCol;
    }

    public boolean isSafe(SnakePoint snakePoint) {
        return isInsideMap(snakePoint) && isSafePoint(snakePoint);

    }

    private boolean isInsideMap(SnakePoint snakePoint) {
        return snakePoint.getX() > 0 && snakePoint.getY() > 0 && snakePoint.getX() < mRow - 1 && snakePoint.getY() < mCol - 1;
    }

    public boolean isSafePoint(SnakePoint snakePoint) {
        return point(snakePoint).getType() == PointType.EMPTY || point(snakePoint).getType() == PointType.FOOD;
    }

    public boolean isFull() {
        for (int i = 0, rowLength = getRow() - 1; i < rowLength; i++) {
            for (int j = 0, colLength = getRow() - 1; j < colLength; j++) {
                if (mPointTypes[i][j].getType() == PointType.EMPTY || mPointTypes[i][j].getType() == PointType.FOOD) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getAreaLength() {
        return areaLength;
    }
}
