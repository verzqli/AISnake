package com.verzqli.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.verzqli.snake.slover.GreedySolver;
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
            if (!map.haveFood()) {
                map.createFood();
            }
            boolean isGameOver = mSnake.move(greedySolver.nextDirect());
            if (!isGameOver) {
                mapView.invalidate();
                mHandler.sendMessageDelayed(mHandler.obtainMessage(), delayTime);
            } else {
                Toast.makeText(MainActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private MapView mapView;
    private Map map;
    private Snake mSnake;
    private HamiltonSolver hamiltonSolver;
    private GreedySolver greedySolver;
    private EditText row;
    private int delayTime = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.map_view);
        row = findViewById(R.id.row);


        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map = new Map(Integer.parseInt(row.getText().toString()), Integer.parseInt(row.getText().toString()));
                mSnake = new Snake(map, Direct.RIGHT, initSnakeBody());
//        hamiltonSolver=new HamiltonSolver(mSnake);
                greedySolver = new GreedySolver(mSnake);
                mapView.setMap(map);
                Message msg = mHandler.obtainMessage();
                mHandler.sendMessageDelayed(msg, delayTime);
            }
        });
        ((SeekBar) findViewById(R.id.time)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                delayTime = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private Deque<SnakePoint> initSnakeBody() {
        Deque<SnakePoint> start = new LinkedList<>();
        start.addFirst(new SnakePoint(1, 1));
        start.addFirst(new SnakePoint(1, 2));
        start.addFirst(new SnakePoint(1, 3));
        return start;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
