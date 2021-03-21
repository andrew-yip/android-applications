package com.example.acorns_numbers_list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AcornAdapter extends ArrayAdapter<Acorn> {

    private Context mContext;
    private ArrayList<Acorn> acorns = new ArrayList<>();

    public AcornAdapter(Activity context, ArrayList<Acorn> acorns){
        super(context, 0, acorns);
        this.mContext = context;
        this.acorns = acorns;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        //TASK 1: GET THE CONTACT OBJECT LOCATED AT THE PROVIDED POSITION IN THE LIST.
        Acorn currentAcorn = acorns.get(position);

        //TASK 2: CHECK IF THE EXISTING VIEW (convertView) IS BEING REUSED: ALREADY IN THE SCRAP PILE - (NOT NULL)
        // OTHERWISE INFLATE THE VIEW.
        View myContactItem = convertView; // IF == NULL THEN INFLATE (NEVER USED), IF != NULL THEN USED ALREADY
        if (myContactItem == null){
            myContactItem = LayoutInflater.from(getContext()).inflate(R.layout.acorn_item, parent, false);
        }

        //TASK 3: FIND THE TEXTVIEWS AND PHOTO IN THE contact_item LAYOUT.
        //        REFERENCE EACH OF THESE VIEWS IN THE CONTACT ITEM
        TextView name_text = (TextView) myContactItem.findViewById(R.id.textView);
        ImageView photo_image = (ImageView) myContactItem.findViewById(R.id.imageView);

        // ADD CONTENT TO THE CONTACT ITEM
        name_text.setText(currentAcorn.label);
        photo_image.setImageResource(currentAcorn.imageId);

        //TASK 4: RETURN THE CONTACT ITEM POPULATED WITH DATA
        return myContactItem;
    }
}
