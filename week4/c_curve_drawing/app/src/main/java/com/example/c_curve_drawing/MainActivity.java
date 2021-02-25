package com.example.c_curve_drawing;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView levelsTV;
    private Integer level;
    private RelativeLayout relativeLayout;
    private FractcalView fractcalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        fractcalView = new FractcalView(this);
        relativeLayout.addView(fractcalView,0);

        level = 1;
        levelsTV = (TextView) findViewById(R.id.textView1);
    }

    public void drawFractcal (View view) {
        fractcalView.level = level;
        fractcalView.invalidate();
    }

    // NUMBERS FOR STEP UP AND STEP DOWN CAN RANGE FROM 1 THROUGH 14
    public void stepUp (View view) {
        if (level < 14) {
            level ++;
            levelsTV.setText(level.toString());
        }
    }

    public void stepDown (View view) {
        if (level > 1) {
            level--;
            levelsTV.setText(level.toString());
        }
    }
}