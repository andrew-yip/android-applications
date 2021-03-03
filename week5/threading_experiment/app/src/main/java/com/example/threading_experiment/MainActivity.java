package com.example.threading_experiment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayLogCat (View view) {
        Thread thread = new Thread() { // annonymous subclass
            public void run () {
                while (true){
                    Log.i("DEMO", "Working on it...");
                }
            }
        };
        thread.start();
    }

    public void displayToast (View view) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, "Hello There!", duration);
        toast.show();
    }
}