package com.example.bee_finds_finger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    // ACTIVITY WORK IS SPLIT INTO TWO THREADS
    // CALCULATING BEE MOVEMENT - BACKGROUND
    // POSITIONING THE BEE - UI THREAD
    private Thread calculateThread;

    private RelativeLayout mainLayout;
    private ImageView beeImageView;
    private ImageView flowerImageView;

    private Flower mFlower;
    private Bee mBee;

    private int xLocation;
    private int yLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TASK 1: WINDOW PROPERTIES ARE SET
        // THIS ANDROID WINDOW WILL NOT FEATURE A TITLE
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // TASK 2: SET THE LAYOUT VIEW
        setContentView(R.layout.activity_main);
        mainLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        // TASK 3: INSTANTIATE THE FLOWER AND BEE
        xLocation = 200;
        yLocation = 200;
        addFlower();
        buildBee();

        // TASK 4: INSTANTIATE THE BACKGROUND THREAD
        calculateThread = new Thread(calculateAction);
    }

    private void addFlower (){

        // TASK 1: CREATE A LAYOUT INFLATER
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // TASK 2: SPECIFY FLOWER POSITION
        int initialXPosition = xLocation;
        int initialYPosition = yLocation;

        mFlower = new Flower();
        mFlower.setX(initialXPosition);
        mFlower.setY(initialYPosition);

        // TASK 3: ADD THE FLOWER
        flowerImageView = (ImageView) layoutInflater.inflate(R.layout.flower_image, null);
        flowerImageView.setX((float) mFlower.getX());
        flowerImageView.setY((float) mFlower.getY() + 50);
    }

    private void buildBee(){
        // TASK 1: CREATE A LAYOUT INFLATER TO ADD VISUAL VIEWS TO THE LAYOUT
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // TASK 2: SPECIFY BEE ATTRIBUTES
        int initialXPosition = xLocation;
        int initialYPosition = yLocation;
        int proportionalVelocity = 10;
        mBee = new Bee();
        mBee.setX(initialXPosition);
        mBee.setY(initialYPosition);
        mBee.setVelocity(proportionalVelocity);

        // TASK 3: ADD THE BEE
        beeImageView = (ImageView) layoutInflater.inflate(R.layout.bee_image, null);
        beeImageView.setX((float) mBee.getX());
        beeImageView.setY((float) mBee.getY());
        mainLayout.addView(beeImageView, 0);
    }

    @Override
    protected void onResume(){
        calculateThread.start();
        super.onResume();
    }

    @Override
    protected void onPause(){
        finish();
        super.onPause();
    }

    @Override
    protected void onDestroy (){
        finish();
        super.onDestroy();
    }

    // RUNNABLE
    private Runnable calculateAction = new Runnable() {
        private static final int DELAY = 200;
        @Override
        public void run() {
            try {
                while (true){
                    mBee.move(xLocation, yLocation);
                    Thread.sleep(DELAY);
                    threadHandler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    };

    // HANDLER FOR UPDATING BEE BETWEEN SLEEP DELAYS
    public Handler threadHandler = new Handler(){
        public void handleMessage(android.os.Message msg){
            // SET THE BEE AT THE CORRECT XY LOCATION
            beeImageView.setX((float) mBee.getX());
            beeImageView.setY((float) mBee.getY());
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event){
        // TASK 1: IDENTIFY THE TOUCH ACTION BEING PERFORMED
        int touchAction = event.getActionMasked();

        // TASK 2: RESPOND TO POSSIBLE TOUCH EVENTS
        switch(touchAction) {
            // BEE FINDS A MOTIONLESS FINGER
            case MotionEvent.ACTION_DOWN:
                xLocation = (int) event.getX();
                yLocation = (int) event.getY();
                // BEE RETURNS TO THE FLOWER WHEN THE FINGER IS REMOVED
            case MotionEvent.ACTION_UP:
                xLocation = mFlower.getX();
                yLocation = mFlower.getY();
                // BEE FOLLOWS A MOVING FINGER
            case MotionEvent.ACTION_MOVE:
                xLocation = (int) event.getX();
                yLocation = (int) event.getY();


        }
        // TASK 3: RETURNS A TRUE AFTER HANDLING THE TOUCH ACTION EVENT
        return true;
    }
}