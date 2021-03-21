package com.example.acorns_numbers_list;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    public ArrayList<Acorn> acorns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acorns = new ArrayList<Acorn>();
        acorns.add(new Acorn("one", R.drawable.number_one, R.raw.number_one));
        acorns.add(new Acorn("two", R.drawable.number_two, R.raw.number_two));
        acorns.add(new Acorn("three", R.drawable.number_three, R.raw.number_three));
        acorns.add(new Acorn("four", R.drawable.number_four, R.raw.number_four));
        acorns.add(new Acorn("five", R.drawable.number_five, R.raw.number_five));
        acorns.add(new Acorn("six", R.drawable.number_six, R.raw.number_six));
        acorns.add(new Acorn("seven", R.drawable.number_seven, R.raw.number_seven));
        acorns.add(new Acorn("eight", R.drawable.number_eight, R.raw.number_eight));
        acorns.add(new Acorn("nine", R.drawable.number_nine, R.raw.number_nine));
        acorns.add(new Acorn("ten", R.drawable.number_ten, R.raw.number_ten));

        AcornAdapter acornAdapter = new AcornAdapter(this, acorns);

        // Find the ListView object in the view hierarchy of the Activity.
        ListView listView = (ListView) findViewById(R.id.listview);

        // Make the listView object use the ContactAdapter created above.
        listView.setAdapter(acornAdapter);

        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                playSound(v, position);
            }
        });
    }

    public void playSound (View view, int position) {
        mediaPlayer = MediaPlayer.create(this, acorns.get(position).soundId);
        mediaPlayer.start();
    }
}