package com.example.tap_button_counter;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // MODEL
    private Counter count;

    //VIEW
    private TextView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        count = new Counter();
        countView = (TextView) findViewById(R.id.textView);
    }

    public void countTap(View view){
        count.addCount();
        countView.setText(count.getCount().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu
        //this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main,menu);
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