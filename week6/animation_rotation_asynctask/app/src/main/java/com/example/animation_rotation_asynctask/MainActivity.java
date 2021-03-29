package com.example.animation_rotation_asynctask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // DATA MEMBERS
    private ImageView toyImage;
    private Toy myToy;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REFERENCES
        toyImage = (ImageView) findViewById(R.id.imageView);
        myToy = new Toy();
    }

    // EVENT HANDLER FOR ON CLICK
    public void rotateAction(View view) {
        if (!myToy.isRotating){
            myToy.isRotating = true;
            myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute(myToy.angle);
        } else {
            myToy.isRotating = false;
        }
    }

    //**************** ASYNCHRONOUS CLASS (DOES IN BACKGROUND) *********************
    private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {
        //**************** WORK IN THE BACKGROUND *********************
        protected Integer doInBackground(Integer... values){
            myToy.angle = values[0];
            while (myToy.isRotating){
                try {
                    //TASK 1: WAIT FOR 1 SECOND
                    Thread.sleep(100);
                    // TASK 2: UPDATE THE ANGLE (CALCULATION)
                    myToy.angle = (myToy.angle + 10) % 360;
                    // TASK 3: SEND AN UPDATE PROGRESS REPORT TO onProgressUpdate()
                     publishProgress(myToy.angle); // publishProgress calls onProgressUpdate
                    //onProgressUpdate(myToy.angle);
                }
                catch (InterruptedException e){
                    //
                }
            }
            return myToy.angle;
        }

        // invoked on the UI thread after a call to publishProgress(Progress...)
        //******* ON PROGRESS UPDATES THE UI INTERFACE VIEWS *********************
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            toyImage.setRotation(values[0]); // does the actual rotation on the UI
        }
    }
}