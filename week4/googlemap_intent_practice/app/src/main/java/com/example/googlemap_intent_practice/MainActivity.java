package com.example.googlemap_intent_practice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Location location;
    EditText latitude_input;
    EditText longitude_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitude_input = (EditText) findViewById(R.id.input_latitude);
        longitude_input = (EditText) findViewById(R.id.input_longitude);
        location = new Location();
        //location.setLatitude(21.258165634);
        //location.setLongitude(-157.819496722);

        // LISTENERS
        latitude_input.addTextChangedListener(latitudeWatcher);
        longitude_input.addTextChangedListener(longitudeWatcher);
    }

    private TextWatcher latitudeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                double latitude = (double) Double.parseDouble(s.toString());
                System.out.println("latitude: " + latitude);
                location.setLatitude(latitude);
            } catch (NumberFormatException e){
                location.setLatitude(0);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher longitudeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                double longitude = (double) Double.parseDouble(s.toString());
                System.out.println("longitude: " + longitude);
                location.setLongitude(longitude);
            } catch (NumberFormatException e){
                location.setLongitude(0);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void takeMe (View view) {
        Uri gmmIntentUri = Uri.parse(location.getLocation());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
        //startActivity(mapIntent);
    }
}