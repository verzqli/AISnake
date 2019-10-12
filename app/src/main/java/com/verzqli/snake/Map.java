package com.verzqli.snake;

import java.util.ArrayList;
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

    public Map(int row, int col) {
        this.mRow = row;
        this.mCol = col;
        mPointTypes = new MapPoint[mRow][mCol];
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mCol; j++) {
                mPointTypes[i][j]=new MapPoint();
            }
        }
        for (int i = 0; i < mRow; i++) {
            mPointTypes[i][0].setType(PointType.WALL);
            mPointTypes[i][col-1].setType(PointType.WALL);
        }
        for (int i = 0; i < mCol; i++) {
            mPointTypes[0][i].setType(PointType.WALL);
            mPointTypes[row-1][i].setType(PointType.WALL);
        }
    }

    public SnakePoint createFood() {
        List<SnakePoint> empty = new ArrayList<>();
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mCol; j++) {
                if (mPointTypes[i][j].getType() == PointType.EMPTY) {
                    empty.add(new SnakePoint(i, j));
                }
            }
        }
        int length = empty.size();
        return empty.get(new Random().nextInt(length));
    }

    public MapPoint[][] getContent() {
        return mPointTypes;
    }

    public  MapPoint point(SnakePoint snakePoint){
        return mPointTypes[snakePoint.getX()][snakePoint.getY()];
    }

    public int getRow() {
        return mRow;
    }

    public int getCol() {
        return mCol;
    }
}
