package com.example.scrolling_numbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i<=20; i++){
            a.add(i);
        }

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, a);

        // Find the ListView object in the view hierarchy of the Activity.
        ListView listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(arrayAdapter);

    }
}