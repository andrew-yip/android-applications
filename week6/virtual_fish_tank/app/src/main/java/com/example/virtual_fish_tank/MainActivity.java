package com.example.virtual_fish_tank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // ANIMATION IS SPLIT INTO TWO THREADS: CALCULATING MOVEMENT
    // FISHTANK UPDATES: UI THREAD

    private Thread calculateMovementThread;

    // FISH TANK ELEMENTS AND PROPERTIES
    private ImageView fishImageView;
    private Fish mFish;
    private int tankWidth;
    private int tankHeight;
    private FrameLayout fishTankLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TASK 1: SET THE LAYOUT
        setContentView(R.layout.activity_main);

        // TASK 2: CREATE REFERENCES TO THE FRAME LAYOUT CONTAINER
        fishTankLayout = (FrameLayout) findViewById(R.id.container);

        // TASK 3: GET THE DIMENSIONS OF THE SCREEN TO USE FOR THE TANK SIZE
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        tankWidth = size.x;
        tankHeight = size.y;

        // TASK 4: INSTANTIATE A FISH
        int initialXPosition = 0;
        int initialYPosition = 0;
        mFish = new Fish(initialXPosition, initialYPosition, Fish.IsSwimming, tankWidth, tankHeight);

        // TASK 5: BUILD THE TANK ELEMENTS
        buildTank();

        // TASK 6: CONSTRUCT THE THREAD TO CALCULATE MOVEMENT AND ANIMATE THE MOVEMENT
        calculateMovementThread = new Thread(calculateMovement);

        // TASK 7: START THE THREAD
        calculateMovementThread.start();
    }

    private void buildTank () {
        // TASK 1: CREATE A LAYOUT INFLATER TO ADD VISUAL VIEWS TO THE LAYOUT
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // TASK 2: ADD THE FOLIAGE
        ImageView foliageImageView = (ImageView) layoutInflater.inflate(R.layout.foliage_layout, null);
        foliageImageView.setX((float) .0);
        foliageImageView.setY((float) .0);
        foliageImageView.setAlpha((float).97);
        fishTankLayout.addView(foliageImageView, 0);

        // TASK 3: ADD THE FOLIAGE
        fishImageView = (ImageView) layoutInflater.inflate(R.layout.fish_image, null);
        fishImageView.setScaleX((float) .3);
        fishImageView.setScaleY((float) .3);
        fishImageView.setX(mFish.x);
        fishImageView.setY(mFish.y);
        fishTankLayout.addView(fishImageView, 0);
    }

    // RUNNABLE OBJECT
    private Runnable calculateMovement = new Runnable() {
        private static final int DELAY = 200;
        @Override
        public void run() {
            try {
                while (true) {
                    mFish.move();
                    Thread.sleep(DELAY);
                    updateTankHandler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    // HANDLER FOR UPDATING THE FISH BETWEEN DELAY SLEEPS
    @SuppressLint("HandlerLeak")
    public Handler updateTankHandler = new Handler() {
        public void handleMessage (android.os.Message msg) {
            // TASK 1: FACE THE FISH IN THE CORRECT DIRECTION
            fishImageView.setScaleX((float) (.3 * mFish.getFacingDirection()));

            // TASK 2: SET THE FISH AT THE CORRECT X,Y LOCATION
            fishImageView.setX((float) mFish.x);
            fishImageView.setY((float) mFish.y);
        }
    };
}