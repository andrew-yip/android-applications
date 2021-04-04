package com.example.fling_billiard_ball;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector aGesture;

    private FrameLayout mainLayout;
    private Thread backgroundThread;
    private ImageView ballImageView;
    private Ball mBall;

    private int left, right, top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TASK 1: SET THE LAYOUT AND THE WINDOW ELEMENTS
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mainLayout = (FrameLayout) findViewById(R.id.frameLayout);

        //TASK 2: CREATE A BILLIARD BALL
        buildBilliardBall();

        //TASK 3: CREATE A GESTURE DETECTOR
        aGesture = new GestureDetector(this, this);

        //TASK 4: CREATE THE BACKGROUND THREAD
        backgroundThread = new Thread(calculateAction);
        backgroundThread.start();
    }

    private void buildBilliardBall() {
        //TASK 1: THE CREATE THE DATA MODEL FOR THE BILLIARD BALL
        mBall = new Ball(0, 0, 150, 0, 0);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mBall.setCollisionBoundaries(metrics.widthPixels, metrics.heightPixels);

        //TASK 2: CREATE A LAYOUT INFLATER TO ADD THE BILLIARD BALL IMAGEVIEW TO THE LAYOUT
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //TASK 3: ADD THE BALL TO THE LAYOUT
        ballImageView = (ImageView) layoutInflater.inflate(R.layout.ball_layout, null);
        ballImageView.setScaleX((float) .3);
        ballImageView.setScaleY((float) .3);
        ballImageView.setX((float) mBall.mX);
        ballImageView.setY((float) mBall.mY);
        mainLayout.addView(ballImageView, 0);
    }


    //**************** RUNNABLE **********************
    private Runnable calculateAction = new Runnable() {
        private static final int DELAY = 50;
        public void run() {
            try {
                while (true) {
                    mBall.move();
                    Thread.sleep(DELAY);
                    threadHandler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e) {
            }
        }
    };
    //****** HANDLER FOR UPDATING THE IMAGEVIEW CONTAINING THE BILLIARD BALL******
    public Handler threadHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //UPDATE BALL LOCATION
            ballImageView.setX((float) mBall.mX);
            ballImageView.setY((float) mBall.mY);
        }
    };



    //***********************TOUCH GESTURES*************************
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return aGesture.onTouchEvent(event);
    }
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        final float ADJUST = 0.025f;
        mBall.mVelocityX = (int) velocityX * ADJUST;
        mBall.mVelocityY = (int) velocityY * ADJUST;
        return true;
    }
    @Override
    public void onLongPress(MotionEvent event) {}
    @Override
    public void onShowPress(MotionEvent event) {}
    @Override
    public boolean onDown(MotionEvent event) {return false;}
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,float distanceX, float distanceY) {return false;}
    @Override
    public boolean onSingleTapUp(MotionEvent event) {return false;}
}