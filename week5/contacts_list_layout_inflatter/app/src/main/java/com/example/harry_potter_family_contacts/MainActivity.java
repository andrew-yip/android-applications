package com.example.harry_potter_family_contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TASK 1: INSTANTIATE THE LAYOUT INFLATER TO INFLATE INDIVIDUAL FRIEND CONTACTS
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService (Context.LAYOUT_INFLATER_SERVICE);

        //TASK 2: CREATE AN ARRAY OF CONTACT OBJECTS.
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        contactList.add(new Contact("Arthur Weasley", "father", R.drawable.male1));
        contactList.add(new Contact("Molly Weasley", "mother", R.drawable.female1));
        contactList.add(new Contact("Bill Weasley", "brother", R.drawable.male2));
        contactList.add(new Contact("Charlie Weasley", "brother", R.drawable.male3));
        contactList.add(new Contact("Fred Weasley", "brother", R.drawable.male4));
        contactList.add(new Contact("George Weasley", "brother", R.drawable.male3));
        contactList.add(new Contact("Percy Weasley", "brother", R.drawable.male2));
        contactList.add(new Contact("Ginny Weasley", "sister", R.drawable.female2));
        contactList.add(new Contact("Ron Weasley", "brother", R.drawable.male1));
        contactList.add(new Contact("Morris Weasley", "brother", R.drawable.male4));
        contactList.add(new Contact("Sadie Weasley", "sister", R.drawable.female3));
        contactList.add(new Contact("Anne Weasley", "sister", R.drawable.female2));
        contactList.add(new Contact("Jon Weasley", "brother", R.drawable.male2));
        contactList.add(new Contact("Kris Weasley", "brother", R.drawable.male1));

        // TASK 3: REFERENCE THE INTERNAL LINEARLAYOUT USED TO DISPLAY THE LIST OF FAMILY CONTACTS
        LinearLayout internal_linearlayout = (LinearLayout)findViewById(R.id.linearlayout);

        //TASK 4: DYNAMICALLY CREATE CONTACT VIEWS FOR EACH CONTACT IN THE
        //        ARRAYLIST INFLATE THE CONTACT AND ADD IT TO THE
        //        SCROLLING INTERNAL LINEAR LAYOUT
        for (int i = 0; i < contactList.size(); i++) {

            //STEP A: INFLATE THE CONTACT ITEM LAYOUT
            LinearLayout myContactItem = (LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.contact_item, null);

            //STEP B: REFERENCE EACH VIEW IN THE CONTACT ITEM
            TextView name_text = (TextView) myContactItem.findViewById(R.id.textView);
            TextView relationship_text = (TextView) myContactItem.findViewById(R.id.textView2);
            ImageView photo_image = (ImageView) myContactItem.findViewById(R.id.imageView);

            //STEP C: ADD CONTENT TO THE CONTACT ITEM
            name_text.setText(contactList.get(i).name);
            relationship_text.setText(contactList.get(i).relationship);
            photo_image.setImageResource(contactList.get(i).imageID);

            //STEP D: ADD THE CONTACT ITEM TO THE LIST
            internal_linearlayout.addView(myContactItem);
        }

    }
}
