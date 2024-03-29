package com.verzqli.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * <pre>
 *     author: XuPei
 *     time  : 2019/10/12
 *     desc  :
 * </pre>
 */
public class MapView extends View {
    private Map mMap;
    private int mBodyWith = 30;
    private int mSpace = 2;
    private Paint mPaint;
    private Paint mFoodPoint;
    private Paint mHeadPoint;
    public MapView(Context context) {
        this(context, null);
    }

    public MapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);
        mFoodPoint=new Paint();
        mFoodPoint.setColor(Color.YELLOW);
        mHeadPoint=new Paint();
        mHeadPoint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mMap!=null){
            MapPoint[][] content= mMap.getContent();
            for (int i = 0,rowLength = mMap.getRow(); i < rowLength; i++) {
                for (int j = 0,colLength = mMap.getCol(); j < colLength; j++) {
                    if (content[i][j].getType()==PointType.WALL){
                        canvas.drawRect(i*(mBodyWith+mSpace),j*(mBodyWith+mSpace),
                                i*(mBodyWith+mSpace)+mBodyWith,j*(mBodyWith+mSpace)+mBodyWith,mPaint);
                    }else if (content[i][j].getType()==PointType.BODY){
                        canvas.drawRect(j*(mBodyWith+mSpace),i*(mBodyWith+mSpace),
                                j*(mBodyWith+mSpace)+mBodyWith,i*(mBodyWith+mSpace)+mBodyWith,mPaint);
                    }else if (content[i][j].getType()==PointType.FOOD){
                        canvas.drawRect(j*(mBodyWith+mSpace),i*(mBodyWith+mSpace),
                                j*(mBodyWith+mSpace)+mBodyWith,i*(mBodyWith+mSpace)+mBodyWith,mFoodPoint);
                    }else if (content[i][j].getType()==PointType.HEAD){
                        canvas.drawRect(j*(mBodyWith+mSpace),i*(mBodyWith+mSpace),
                                j*(mBodyWith+mSpace)+mBodyWith,i*(mBodyWith+mSpace)+mBodyWith,mHeadPoint);
                    }
                }
            }
        }
    }

    public Map getMap() {
        return mMap;
    }

    public void setMap(Map mMap) {
        this.mMap = mMap;
    }
}
