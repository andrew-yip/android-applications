package com.example.email_coffee_ordering_app_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // MODEL
    public Coffee coffee;

    // DECLARING REFERENCES TO VIEWS
    public TextView quantity;
    public TextView summary;
    public CheckBox hasChocolate;
    public CheckBox hasWhippedCream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INSTANTIATE MODEL
        coffee = new Coffee();

        // REFERENCING VIEWS (need to reference views for everything you see on screen)
        // we need all of this from the screen to be passed
        // views for the checkboxes
        hasWhippedCream = (CheckBox) findViewById(R.id.checkBox4);
        hasChocolate = (CheckBox) findViewById(R.id.checkBox3);
        // views for the text views
        quantity = (TextView) findViewById(R.id.textView4);
        summary = (TextView) findViewById(R.id.textView6);
    }

    // CONTROLLER METHODS/FUNCTIONALITY

    public void setChocolate (View view){
        coffee.setChocolate(hasChocolate.isChecked());
    } // determines if its checked or not

    public void setHasWhippedCream (View view){
        coffee.setWhippedCream(hasWhippedCream.isChecked()); // determines if its checked or not
    }

    public void increment(View view){
        coffee.incrementQuantity(); // incrementing coffee count
        quantity.setText(coffee.getQuantityString()); // setting the quantity
    }

    public void decrement(View view){
        coffee.decrementQuantity(); // decrementing the coffee quantity
        quantity.setText(coffee.getQuantityString()); // setting the amount
    }

    public void computeOrder(View view){
        summary.setText(coffee.getOrderSummary());
    }

    public void sendEmail (View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"myemail@gmail.com"}); // recipients
        intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject: ");
        intent.putExtra(Intent.EXTRA_TEXT, coffee.getOrderSummary());
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Send Email:"));
    }
}