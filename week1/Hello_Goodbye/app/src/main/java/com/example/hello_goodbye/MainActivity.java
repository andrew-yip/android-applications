package com.example.hello_goodbye;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Declare text reference to the interface layout component
    private TextView greetingTextView;

    //Indicates hello is currently displayed
     private boolean isHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TASK 1: INFLATE THE MAIN SCREEN LAYOUT USED BY THE APP
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TASK 2: ESTABLISH REFRENCES TO THE TEXTVIEW AND BUTTON
        greetingTextView = (TextView)findViewById(R.id.textView);
        //TASK 3: INITIALIZE GREETINGS
        initializeGreeting();
        //TASK 4: REGISTER THE LISTENER EVENT FOR THE BUTTON
        Button exclaimBtn = (Button) findViewById(R.id.button);
        exclaimBtn.setOnClickListener(toggleGreeting);
        //setContentView(R.layout.activity_main);
    }

    private final View.OnClickListener toggleGreeting = new View.OnClickListener(){

        public void onClick(View btn){
            // TASK 1: CONSTRUCT THE TOGGLE GREETING
            if(isHello){
                isHello = false;
                greetingTextView.setText(R.string.goodbye);
            } else {
                isHello = true;
                greetingTextView.setText(R.string.hello);
            }
        }
    };

    public void initializeGreeting(){
        isHello = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu
        // this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}