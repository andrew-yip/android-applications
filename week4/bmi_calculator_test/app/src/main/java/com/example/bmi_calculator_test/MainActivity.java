package com.example.bmi_calculator_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.RadioGroup.OnCheckedChangeListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // MODEL
    BMI bmi;

    // REFERENCES TO VIEWS
    public TextView inches;
    public TextView pounds;
    public TextView output;
    public RadioGroup units;

    // display changing units
    public TextView heightUnits;
    public TextView weightUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REFERENCES TO VIEWS
        inches = (TextView) findViewById(R.id.textView5);
        pounds = (TextView) findViewById(R.id.textView6);
        output = (TextView) findViewById(R.id.textView7);
        units = (RadioGroup) findViewById(R.id.radioGroup);

        heightUnits = (TextView) findViewById(R.id.textView2);
        weightUnits = (TextView) findViewById(R.id.textView4);

        // CREATE MODEL
        bmi = new BMI();

        // REGISTER CHANGE LISTENER
        registerChangeListener();

    }

    public void registerChangeListener () {
        units.setOnCheckedChangeListener(unitListener);
    }

    public OnCheckedChangeListener unitListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioButton1: // if you select metric
                    bmi.setUnits("metric");
                    heightUnits.setText("Centimeters: ");
                    weightUnits.setText("Kilometers: ");
                    break;
                case R.id.radioButton2: // if you select standard
                    // set bmi to standard
                    bmi.setUnits("standard");
                    heightUnits.setText("Inches: ");
                    weightUnits.setText("Pounds: ");
                    break;
            }
        }
    };

    public void increment (View view) {
        if (view.getContentDescription().equals("increment inches")){
            bmi.incrementInches();
            inches.setText(bmi.getInches().toString());
        } else {
            bmi.incrementPounds();
            pounds.setText(bmi.getWeight().toString());
        }
    }

    public void decrement (View view) {
        if (view.getContentDescription().equals("decrement inches")){
            bmi.decrementInches();
            inches.setText(bmi.getInches().toString());
        } else {
            bmi.decrementPounds();
            pounds.setText(bmi.getWeight().toString());
        }
    }

    public void calculateBMI (View view) {
        output.setText("Your BMI is: " + String.format("%.2f", bmi.calculateTotal()));
    }
}