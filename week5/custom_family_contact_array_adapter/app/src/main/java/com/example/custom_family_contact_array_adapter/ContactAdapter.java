package com.example.custom_family_contact_array_adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context mContext;
    private ArrayList<Contact> contacts = new ArrayList<>();

    public ContactAdapter(Activity context, ArrayList<Contact> contacts){
        super(context, 0, contacts);
        this.mContext = context;
        this.contacts = contacts;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        //TASK 1: GET THE CONTACT OBJECT LOCATED AT THE PROVIDED POSITION IN THE LIST.
        Contact currentContact = contacts.get(position);

        //TASK 2: CHECK IF THE EXISTING VIEW (convertView) IS BEING REUSED: ALREADY IN THE SCRAP PILE - (NOT NULL)
        // OTHERWISE INFLATE THE VIEW.
        View myContactItem = convertView; // IF == NULL THEN INFLATE (NEVER USED), IF != NULL THEN USED ALREADY
        if (myContactItem == null){
            myContactItem = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        }

        //TASK 3: FIND THE TEXTVIEWS AND PHOTO IN THE contact_item LAYOUT.
        //        REFERENCE EACH OF THESE VIEWS IN THE CONTACT ITEM
        TextView name_text = (TextView) myContactItem.findViewById(R.id.textView);
        TextView relationship_text = (TextView) myContactItem.findViewById(R.id.textView2);
        ImageView photo_image = (ImageView) myContactItem.findViewById(R.id.imageView);

        // ADD CONTENT TO THE CONTACT ITEM
        name_text.setText(currentContact.name);
        relationship_text.setText(currentContact.relationship);
        photo_image.setImageResource(currentContact.imageID);

        //TASK 4: RETURN THE CONTACT ITEM POPULATED WITH DATA
        return myContactItem;
    }
}
