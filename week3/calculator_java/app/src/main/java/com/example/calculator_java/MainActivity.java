package com.example.calculator_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // data members
    private TextView mNumberDisplay;
    private Calculation calculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REFERENCING VIEWS OF VALUES CLICKED AND CALCULATED
        mNumberDisplay = (TextView) findViewById(R.id.textView);

        // this is the model of the calculation itself
        calculation = new Calculation();
    }

    // CLEARS THE OPERATIONS
    public void goAC (View view) {
        calculation.clearOperands();
        mNumberDisplay.setText("0");
    }

    // WHEN YOU CLICK A NUMBER
    public void goOperand (View view) {
        String val = (String) mNumberDisplay.getText(); // what is on the display at the point
        String digit = (String) view.getContentDescription(); // just the digit thats clicked

        //System.out.println("digit: " + digit);
        //System.out.println("what is currently on the screen: " + (String) mNumberDisplay.getText());
        //System.out.println("concattination: " + (String) mNumberDisplay.getText() + digit.charAt(0));

        // WHAT TO DISPLAY ON THE SCREEN
        if (val.charAt(0) == '0'){ // if its the first number you put in then set the screen text to the digit
            mNumberDisplay.setText(digit);
        } else {
            mNumberDisplay.setText((String) mNumberDisplay.getText() + digit.charAt(0));
        }
    }

    // TO ADD, SUBTRACT, MULTIPLY, DIVIDE, MOD, (ALL THE OPERATIONS)
    public void goOperator (View view) {
        String operator = (String) view.getContentDescription();
        try {
            String val = (String) mNumberDisplay.getText();
            calculation.setOperand1((int) Integer.parseInt(val.toString()));
        } catch (NumberFormatException e){
            calculation.setOperand1(0);
        }
        mNumberDisplay.setText("0");
        calculation.setOperator(operator);
    }

    // WHEN YOU CLICK THE EQUALS BUTTON
    public void goCompute (View view) {
        try {
            //GETS THE SECOND VALUE
            String val = (String) mNumberDisplay.getText();
            calculation.setOperand2((int) Integer.parseInt(val.toString()));
        }
        catch (NumberFormatException e){
            calculation.setOperand2(0);
        }

        // SETS THE TEXT WHEN CLICKING THE EQUAL BUTTON
        mNumberDisplay.setText(calculation.getmValue().toString());
    }
}