package com.example.stopwatch_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // UI ELEMENTS: BUTTONS WILL TOGGLE IN VISIBILITY
    private TextView timeDisplay;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    // TIME ELEMENTS
    private WatchTime watchTime;
    private long timeInMilliseconds = 0L;

    // THE HANDLER FOR THE THREAD ELEMENT
    //private Handler mHandler;
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TASK 1: ACTIVATE THE ACTIVITY AND THE LAYOUT
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TASK 2: CREATE REFERENCES TO UI COMPONENTS
        timeDisplay = (TextView) findViewById(R.id.textView1);
        startBtn = (Button) findViewById(R.id.button1);
        stopBtn = (Button) findViewById(R.id.button2);
        resetBtn = (Button) findViewById(R.id.button3);

        // TASK 3: HIDE THE STOP BUTTON
        stopBtn.setEnabled(false);
        resetBtn.setEnabled(false);

        // TASK 4: INSTANTIATE THE OBJECT THAT MODELS THE STOPWATCH TIME
        watchTime = new WatchTime();

    }

    public void startTimer(View view) {
        // TASK 1: SET THE START BUTTON TO INVISIBLE AND THE STOP BUTTON TO VISIBLE
        stopBtn.setEnabled(true);
        startBtn.setEnabled(false);
        resetBtn.setEnabled(false);

        // TASK 2: SET THE START TIME AND CALL THE CUSTOM HANDLER
        watchTime.setStartTime(SystemClock.uptimeMillis());
        watchTime.timeFlowing = true; // setting time flowing to true
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(SystemClock.uptimeMillis());

    }

    public void stopTimer(View view) {
        watchTime.timeFlowing = false;
        // TASK 1: DISABLE THE START BUTTON AND ENABLE THE STOP BUTTON
        stopBtn.setEnabled(false);
        startBtn.setEnabled(true);
        resetBtn.setEnabled(true);

        // TASK 2: UPDATE THE STORED TIME VALUE
        watchTime.addStoredTime(timeInMilliseconds);

    }

    public void resetTimer(View view) {
        watchTime.resetWatchTime();
        timeInMilliseconds = 0L;

        // SETS TIME TO 00:00:00
        int minutes = 0;
        int seconds = 0;

        // TASK 3: DISPLAY THE TIME IN THE TEXTVIEW
        timeDisplay.setText(String.format("%02d", minutes)+ ":" + String.format("%02d", seconds));
    }

    private class MyAsyncTask extends AsyncTask<Long, Long, Long> {

        @Override
        protected Long doInBackground(Long... Longs) {

            while (watchTime.timeFlowing){
                try {
                    //TASK 1: WAIT FOR 1 SECOND
                    Thread.sleep(0);
                    watchTime.timeFlowing = true;
                    // TASK 1: COMPUTE THE TIME DIFFERENCE
                    timeInMilliseconds = SystemClock.uptimeMillis()  - watchTime.getStartTime();
                    watchTime.setTimeUpdate(watchTime.getStoredTime() + timeInMilliseconds);
                    System.out.println(timeInMilliseconds);
                    publishProgress(timeInMilliseconds);
                }
                catch (InterruptedException e){
                    System.out.println("Error: " + e);
                }
            }
            return timeInMilliseconds;
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);

            int time = (int) (watchTime.getTimeUpdate() / 1000);

            // TASK 2: COMPUTE MINUTES, SECONDS, AND MILLISECONDS
            int minutes = time / 60;
            int seconds = time % 60;

            // TASK 3: DISPLAY THE TIME IN THE TEXT VIEW
            timeDisplay.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));

        }
    }
}