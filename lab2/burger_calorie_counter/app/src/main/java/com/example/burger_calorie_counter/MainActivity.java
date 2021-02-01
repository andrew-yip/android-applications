package com.example.burger_calorie_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    // CREATE BURGER MODEL
    private Burger burger;

    // DECLARE UI REFERENCES TO BE REFERENCED
    private RadioGroup patty_radioGroup; // determining which patty you have
    private CheckBox prosciutto_checkBox; // checkbox for the cheese
    private RadioGroup cheese_radioGroup; // determining which cheese
    private SeekBar sauce_seekBar; // how much sauce
    private TextView caloriesTotal; // reference to the total calories

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INSTANTIATE BURGEER
        burger = new Burger();
        initialize();

        // REGISTER CHANGE LISTENERS
        registerChangeListener();
    }

    private void initialize() {
        // REFERENCES TO THE VIEWS
        patty_radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        prosciutto_checkBox = (CheckBox) findViewById(R.id.checkBox);
        cheese_radioGroup = (RadioGroup) findViewById(R.id.radioGroup3);
        sauce_seekBar = (SeekBar) findViewById(R.id.seekBar);
        caloriesTotal = (TextView) findViewById(R.id.textView3);

        // displays initial calories
        displayCalories();
    }

    // REGISTERING LISTENERS
    private void registerChangeListener () {
        patty_radioGroup.setOnCheckedChangeListener(foodListener);
        prosciutto_checkBox.setOnClickListener(prosciuttoListener);
        cheese_radioGroup.setOnCheckedChangeListener(foodListener);
        sauce_seekBar.setOnSeekBarChangeListener(sauceListener);
    }

    private void displayCalories () {
        String calorieText = "Calories: " + burger.getTotalCalories();
        caloriesTotal.setText(calorieText);
    }

    // LISTENERS

        // LISTENER FOR THE FOOD (BEEF, LAMB, OSTRICH, ASIAGO, CREME)
    private OnCheckedChangeListener foodListener = new OnCheckedChangeListener(){
        public void onCheckedChanged(RadioGroup rbGroup, int radioId) {
            switch (radioId) {
                case R.id.radioButton1: // BEEF PATTY
                    burger.setmPattyCal(Burger.BEEF);
                    break;
                case R.id.radioButton2:
                    burger.setmPattyCal(Burger.LAMB);
                    break;
                case R.id.radioButton3: // OSTRICH PATTY
                    burger.setmPattyCal(Burger.OSTRICH);
                    break;
                case R.id.radioButton7: // ASIAGO CHEESE
                    burger.setmPattyCal(Burger.ASIAGO);
                    break;
                case R.id.radioButton8: // CREME FRAICHE
                    burger.setmPattyCal(Burger.CREME_FRAICHE);
                    break;
            }
            displayCalories();
        }
    };

        // LISTENER FOR THE CHECK BOXES
    private OnClickListener prosciuttoListener = new View.OnClickListener() {
        public void onClick (View v) {
            if (((CheckBox) v).isChecked()) {
                burger.setmProsciuttoCal(Burger.PROSCIUTO);
            } else {
                burger.clearProsciuttoCalories();
            }
            displayCalories();
        }
    };

    private SeekBar.OnSeekBarChangeListener sauceListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            burger.setmSauceCal(seekBar.getProgress());
            displayCalories();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}