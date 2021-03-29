package com.example.backgroundthread_counting_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Counter counter;
    TextView displayedCount;
    Handler mHandler;
    Button startBtn;
    Button stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REFERENCES TO UI COMPONENTS
        displayedCount = (TextView) findViewById(R.id.textView);
        mHandler = new Handler();
        startBtn = (Button) findViewById(R.id.button);
        stopBtn = (Button) findViewById(R.id.button2);

        // COUNTER OBJECT INSTANTIATED
        counter = new Counter();

    }

    public void startCount (View view) {
        // SET THE START BUTTON TO INVISIBLE
        // SET THE TOP BUTTON TO VISIBLE
        stopBtn.setEnabled(true);
        startBtn.setEnabled(false);

        // MAKE THE INITIAL CALL TO CREATE THE RUNNABLE TASK AND POST IT TO THE MESSAGE QUEUE
        mHandler.postDelayed(updateCountRunnable, 500);
    }

    // RUNNABLE OBJECT
    private Runnable updateCountRunnable = new Runnable() {
        @Override
        public void run() {
            counter.setCountUpdate(); // UPDATE THE COUNT
            displayedCount.setText(counter.toString()); // DISPLAY COUNT IN TEXTVIEW

            mHandler.postDelayed(this, 500); // CONTINUE TO POST TO THE MESSAGEQUEUE, SPECIFY TIME LAPSE
        }
    };

    public void stopCount (View view) {
        // DISABLE THE START BUTTON AND ENABLE THE STOP BUTTON
        stopBtn.setEnabled(false);
        startBtn.setEnabled(true);
        mHandler.removeCallbacks(updateCountRunnable); // HANDLER CLEARS THE MESSAGE QUEUE
    }
}