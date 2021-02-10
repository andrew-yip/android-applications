package com.example.shipping_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MenuItem;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    // DATA MEMBERS FOR SHIP ITEM
    private ShipItem shipItem;

    // REFERENCES TO THE VIEWS
    private EditText weightEntered;
    private TextView baseCostTotalValue;
    private TextView addedCostTotalValue;
    private TextView totalCostTotalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INSTANTIATE SHIPPING ITEM
        shipItem = new ShipItem();

        // INSTANTIATING VIEWS

            // INPUT VIEW
        weightEntered = (EditText) findViewById(R.id.plain_text_input);
            // OUTPUT VIEW
        baseCostTotalValue = (TextView) findViewById(R.id.baseCost);
        addedCostTotalValue = (TextView) findViewById(R.id.addedCost);
        totalCostTotalValue = (TextView) findViewById(R.id.totalShippingCost);

        // REGISTER AN LISTENER EVENT FOR WEIGHT INPUT
        weightEntered.addTextChangedListener(weightTextWatcher);
    }

    // LISTENS FOR WHEN TEXT IS CHANGED
    private TextWatcher weightTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        // THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE
        // THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                shipItem.setWeight((int) Double.parseDouble(s.toString())); // sets the weight every time the text is changed
            } catch (NumberFormatException e){
                shipItem.setWeight(0);
            }
            displayShipping(); // display the new calculated shipping every time the text is changed
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void displayShipping (){
        baseCostTotalValue.setText("$" + String.format("%.02f", shipItem.getBaseCost()));
        addedCostTotalValue.setText("$" + String.format("%.02f", shipItem.getAddedCost()));
        totalCostTotalValue.setText("$" + String.format("%.02f", shipItem.getTotalCost()));
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