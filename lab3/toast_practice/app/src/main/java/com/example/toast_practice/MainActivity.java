package com.example.toast_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // EXPERIMENTING WITH KEY EVENTS
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "ON CREATE LAUNCHED", Toast.LENGTH_LONG).show();
        key =  "message";
        Log.v(key, "ON CREATE LAUNCED");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this, "ON START LAUNCHED", Toast.LENGTH_LONG).show();
        Log.v(key, "ON start LAUNCED");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this, "ON RESUME LAUNCHED", Toast.LENGTH_LONG).show();
        Log.v(key, "ON RESUME LAUNCED");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(this, "ON PAUSE LAUNCHED", Toast.LENGTH_LONG).show();
        Log.v(key, "ON PAUSE LAUNCED");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(this, "ON STOP LAUNCHED", Toast.LENGTH_LONG).show();
        Log.v(key, "ON STOP LAUNCED");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(this, "ON RESTART LAUNCHED", Toast.LENGTH_LONG).show();
        Log.v(key, "ON RESTART LAUNCED");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "ON DESTROY LAUNCHED", Toast.LENGTH_LONG).show();
        Log.v(key, "ON DESTROY LAUNCED");
    }

    public void iAmClicked (View view){
        Toast.makeText(this, "I am the button that was clicked", Toast.LENGTH_LONG).show();
        Log.v(key, "BUTTON CLICKED");
    }
}