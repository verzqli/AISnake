package com.verzqli.snake;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/11
 *     desc  :
 * </pre>
 */
public class TableCell {
    private TableCell parent;
    private int distance;
    private boolean visit;

    public TableCell() {
        reset();
    }

    public TableCell getParent() {
        return parent;
    }

    public void setParent(TableCell parent) {
        this.parent = parent;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public void reset() {
        this.distance = Integer.MAX_VALUE;
        this.parent = null;
        this.visit = false;
    }

}
