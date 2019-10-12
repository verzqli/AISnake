package com.verzqli.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.verzqli.snake.slover.HamiltonSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSnake.move();
            mapView.invalidate();
            mHandler.sendMessageDelayed(mHandler.obtainMessage(), 60);
        }
    };
    private MapView mapView;
    private Map map;
    private Snake mSnake;
    private HamiltonSolver hamiltonSolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.map_view);
        map = new Map(18, 18);
        mSnake=new Snake(map,Direct.RIGHT,initSnakeBody());
        hamiltonSolver=new HamiltonSolver(mSnake);
        mapView.setMap(map);
        Message msg = mHandler.obtainMessage();
        mHandler.sendMessageDelayed(msg, 30);



        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnake.setNextDirect(Direct.UP);
            }
        });
        findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnake.setNextDirect(Direct.DOWN);
            }
        });
        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnake.setNextDirect(Direct.LEFT);
            }
        });
        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnake.setNextDirect(Direct.RIGHT);
            }
        });

    }

    private Deque<SnakePoint> initSnakeBody() {
        Deque<SnakePoint> start = new LinkedList<>();
        start.addFirst(new SnakePoint(1,1));
        start.addFirst(new SnakePoint(1,2));
        start.addFirst(new SnakePoint(1,3));
        start.addFirst(new SnakePoint(1,4));
        start.addFirst(new SnakePoint(1,5));
        start.addFirst(new SnakePoint(1,6));
        return start;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
