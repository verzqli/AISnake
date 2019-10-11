package com.verzqli.snake;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/11
 *     desc  :
 * </pre>
 */
public class Map {
    private PointType mPointTypes[][];
    private int mRow;
    private int mCol;
    private SnakePoint mFood;
    public Map(int row,int col) {
        this.mRow = row;
        this.mCol=col;
        mPointTypes=new PointType[mRow][mCol];

    }
}
