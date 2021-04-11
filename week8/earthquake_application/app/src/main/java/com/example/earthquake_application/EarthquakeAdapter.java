package com.example.earthquake_application;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter {
    public EarthquakeAdapter (Activity context, ArrayList earthquakeList) {
        super (context, 0, earthquakeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //TASK 1: Check if there is an existing list item view to be recycled.
        // Otherwise, if convertView is null, inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //TASK 2: Find the earthquake at the given position in the list of earthquakes.
        Earthquake currentQuake = (Earthquake) getItem(position);

        //TASK 3: find the TextViews in the list item and populate them with data from
        //        the currentQuake data.
        TextView magTextView = (TextView) listItemView.findViewById(R.id.textView1);
        magTextView.setText(currentQuake.mag);
        TextView cityTextView = (TextView) listItemView.findViewById(R.id.textView2);
        cityTextView.setText(currentQuake.city);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.textView3);
        dateTextView.setText(currentQuake.mDate);

        //TASK 4: return the list item view that is now showing the appropriate data
        return listItemView;
    }

}
