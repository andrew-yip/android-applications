package com.example.lightbulb_shake_gesture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    //ImageView img;
    boolean lightOn;

    //LIGHT ELEMENTS
    private final int ON = 1;
    private final int OFF = 0;
    private ImageView lightImage;
    private int lightStatus;

    //SENSOR SHAKE ELEMENTS
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private MyShakeListener mShakeDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TASK 1: DEFAULT ELEMENTS
        //img = (ImageView) findViewById(R.id.lightBulb);
        lightImage = (ImageView) findViewById(R.id.lightBulb);
        lightStatus = OFF;
        //lightOn = false;

        /*img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lightOn) {
                    img.setImageResource(R.drawable.light_off);
                    lightOn = false;
                } else {
                    img.setImageResource(R.drawable.light_on);
                    lightOn = true;
                }
            }
        });*/

        //TASK 2: REGISTER THE SENSOR MANAGER AND SETUP THE SHAKE LISTENER
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mShakeDetector = new MyShakeListener(new MyShakeListener.OnShakeListener(){
            @Override
            public void onShake() {
                if (lightStatus == ON) {
                    lightStatus = OFF;
                    lightImage.setImageResource(R.drawable.light_off);
                }
                else {
                    lightStatus = ON;
                    lightImage.setImageResource((R.drawable.light_on));
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mSensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector, mSensorAccelerometer);
        super.onPause();
    }

    public void toggleLight(View view){
        Log.v("gForce ", "GForce");
        /*
        if (lightStatus == ON) {
            lightStatus = OFF;
            lightImage.setImageResource(R.mipmap.light_off);
        }
        else {
            lightStatus = ON;
            lightImage.setImageResource((R.mipmap.light_on));
        }

         */
    }

}
