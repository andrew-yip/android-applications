package com.example.cookie_monster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;

//CONTROLLER

public class MainActivity extends AppCompatActivity {

    // MODEL
    private CookieMonster monstah;

    // VIEW
    private TextView hungerStatus; // view for the status of how hungry they
    private TextView hungerButton; // view for the visualization of changing button text
    private ImageView face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INITIALIZE VIEWS
        hungerStatus = (TextView)findViewById(R.id.textView);
        hungerButton = (TextView)findViewById(R.id.button);
        face = (ImageView)findViewById(R.id.imageView);

        // INITIALIZING MODEL AND VIEW FOR THE MODEL
        monstah = new CookieMonster();

        // MAKING BUTTON
        Button btnView = (Button)findViewById(R.id.button);
        btnView.setOnClickListener(toggleHunger);
    }

    private final View.OnClickListener toggleHunger = new View.OnClickListener(){
        public void onClick(View view){
            if (monstah.isHungry){
                monstah.isHungry = false;
                hungerStatus.setText(R.string.full);
                hungerButton.setText(R.string.done);
                face.setImageResource(R.mipmap.full);
            } else {
                monstah.isHungry = true;
                hungerStatus.setText(R.string.hungry);
                hungerButton.setText(R.string.eat);
                face.setImageResource(R.mipmap.hungry);
            }
        }
    };
}