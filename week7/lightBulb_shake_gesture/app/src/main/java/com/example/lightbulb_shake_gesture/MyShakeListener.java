package com.example.lightbulb_shake_gesture;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;


public class MyShakeListener implements SensorEventListener {

    // TASK 1: IDENTIFY THE MEMBER METHODS
    private long mTimeOfLastShake;
    private static final float SHAKE_THRESHOLD = 22.0f;
    private static final int SHAKE_TIME_LAPSE = 500; // in miliseconds

    private OnShakeListener mShakeListener;

    // CONSTRUCTOR
    public MyShakeListener(OnShakeListener shakeListener) {
        mShakeListener = shakeListener;
    }

    //ABSTRACT METHOD WE NEED IMPLEMENT
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            //TASK 1: COLLECT THE RAW DATA FROM THE SENSOR
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            //TASK 2:  REMOVE GRAVITY FROM THE READINGS
            float gForceX = x - SensorManager.GRAVITY_EARTH;
            float gForceY = y - SensorManager.GRAVITY_EARTH;
            float gForceZ = z - SensorManager.GRAVITY_EARTH;

            //TASK 3: COMPUTE THE DIRECTIONLESS FORCE
            double value = Math.pow(gForceX, 2.0) + Math.pow(gForceY, 2.0) + Math.pow(gForceZ, 2.0);
            float gForce = (float) Math.sqrt(value);

            //TASK 4: OPTIONAL: SEND DATA TO THE LOG CAT.
            String gForceOutput = " " + gForce;
            Log.v("gForce ", gForceOutput);

            //TASK 5: CHECK IF WE HAVE DETECTED A SHAKE
            if (gForce > SHAKE_THRESHOLD){
                final long now = System.currentTimeMillis();
                if (mTimeOfLastShake + SHAKE_TIME_LAPSE > now) {
                    return;
                }
                mTimeOfLastShake = now;
                mShakeListener.onShake();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public interface OnShakeListener {
        public void onShake();
    }

}
