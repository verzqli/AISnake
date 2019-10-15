package com.verzqli.snake;

import android.graphics.Point;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/12
 *     desc  :
 * </pre>
 */
public class MapPoint {
    private PointType type;

    public MapPoint() {
        this.type = PointType.EMPTY;
    }
    public MapPoint(PointType type) {
        this.type =type;
    }
    public PointType getType() {
        return type;
    }

    public void setType(PointType type) {
        this.type = type;
    }


}
